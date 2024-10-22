package com.example.dao;

import com.example.model.Note;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class NoteDao {

    // Simpan atau update catatan
    public void saveOrUpdate(Note note) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(note);  // Menyimpan atau memperbarui catatan
            System.out.println("Note berhasil disimpan: " + note.getTitle());  // Debugging
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Ambil semua catatan
    public List<Note> getAllNotes() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Note> query = session.createQuery("from Note", Note.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Ambil catatan berdasarkan ID
    public Note getNoteById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Note.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Hapus catatan berdasarkan ID
    public void deleteNote(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Note note = session.get(Note.class, id);
            if (note != null) {
                session.remove(note); // Menggunakan 'remove'
            } else {
                System.out.println("Catatan dengan ID " + id + " tidak ditemukan.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
