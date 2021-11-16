package com.example.proyectotesting.patterns.behavioral.iterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BookShopTest {
    @BeforeEach
    void setUp() {
        System.out.println("Ejecutando Test");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Finalizando Test");
    }


    @Test
    void addBook() {
        Book b1 = new Book("888", "Vicente", 2040);

        BookShop book_shop = new BookShop();

        Iterator<Book> iter = book_shop.iterator();

        assertFalse(iter.hasNext());

        book_shop.addBook(b1);

        b1.setAuthor("Federico");
        b1.setIsbn("333");
        b1.setYear(2010);

        b1.toString();
        assertTrue(iter.hasNext());
        assertEquals("Federico", b1.getAuthor());
        assertEquals("333", b1.getIsbn());
        assertEquals(2010, b1.getYear());

    }

    @Test
    void iterator() {


        Book b1 = new Book("888", "Vicente", 2040);
        Book b2 = new Book("777", "Vegeta", 2060);
        Book b3 = new Book("444", "Miguel", 2080);


        BookShop book_shop = new BookShop();

        book_shop.addBook(b1);
        book_shop.addBook(b2);
        book_shop.addBook(b3);

        Iterator<Book> iter = book_shop.iterator();


        // 1. Iteramos primer book
        assertTrue(iter.hasNext());

        b1 = iter.next();
        assertEquals("Vicente", b1.getAuthor());

        // 2. Iteraramos segundo book
        assertTrue(iter.hasNext());

        b2 = iter.next();
        assertEquals("Vegeta", b2.getAuthor());

        // 2. Iteraramos tercer book
        assertTrue(iter.hasNext());

        b3 = iter.next();
        assertEquals("Miguel", b3.getAuthor());

        // 3. Comprobacion que no hay mas libros que iterar
        assertFalse(iter.hasNext());

    }

    @DisplayName("Comprobamos que se arroja una excepcion si no se encuentra un libro")
    @Test
    void bookExceptionTest() {
        Book b1 = new Book("888", "Vicente", 2040);
        BookShop book_shop = new BookShop();
        book_shop.addBook(b1);
        Iterator<Book> iter = book_shop.iterator();
        b1 = iter.next();


//        //assertDoesNotThrow((Executable) iter.next());
        assertThrows(NoSuchElementException.class, () -> iter.next());
        assertFalse(iter.hasNext());
    }


}