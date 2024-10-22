package com.example.servlet;

import com.example.dao.NoteDao;
import com.example.model.Note;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class NoteServlet extends HttpServlet {
    private NoteDao noteDao = new NoteDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                showForm(request, response);  // Menampilkan form kosong untuk catatan baru
                break;
            case "edit":
                showForm(request, response);  // Menampilkan form dengan data catatan yang akan diedit
                break;
            case "delete":
                deleteNote(request, response);  // Menghapus catatan
                break;
            default:
                listNotes(request, response);  // Menampilkan daftar catatan
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        if (title == null || title.isEmpty() || content == null || content.isEmpty()) {
            request.setAttribute("error", "Judul dan konten tidak boleh kosong.");
            request.getRequestDispatcher("/note-form.jsp").forward(request, response);
            return;
        }

        Note note = new Note();
        if (id != null && !id.isEmpty()) {
            note.setId(Integer.parseInt(id));  // Jika ada ID, berarti update
        }
        note.setTitle(title);
        note.setContent(content);

        // Simpan atau update catatan melalui DAO
        noteDao.saveOrUpdate(note);

        // Perbaikan: Alihkan ke halaman daftar catatan menggunakan redirect
        response.sendRedirect(request.getContextPath() + "/note?action=list");
    }

    private void listNotes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Note> notes = noteDao.getAllNotes();
        request.setAttribute("notes", notes);

        // Menampilkan note-list.jsp
        request.getRequestDispatcher("/note-list.jsp").forward(request, response);
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        Note note = null;

        if (id != null && !id.isEmpty()) {
            note = noteDao.getNoteById(Integer.parseInt(id));
        }

        request.setAttribute("note", note);
        request.getRequestDispatcher("/note-form.jsp").forward(request, response);
    }

    private void deleteNote(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            noteDao.deleteNote(Integer.parseInt(id));
        }
        response.sendRedirect(request.getContextPath() + "/note?action=list");
    }
}
