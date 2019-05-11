package com.kodilla.patterns2.adapter.bookclassifier;

import com.kodilla.patterns2.adapter.bookclasifier.MedianAdapter;
import com.kodilla.patterns2.adapter.bookclasifier.librarya.Book;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class MedianAdapterTestSuite {
    @Test
    public void shouldReturnMedianYear2007() {
        //Given
        Set<Book> books = new HashSet<>();
        books.add(new Book("Author 1", "Title1", 2018, "1"));
        books.add(new Book("Author 2", "Title2", 2007, "2"));
        books.add(new Book("Author 3", "Title3", 2006, "3"));
        books.add(new Book("Author 4", "Title4", 2008, "4"));
        books.add(new Book("Author 4", "Title4", 1990, "4"));
        //When
        MedianAdapter medianAdapter = new MedianAdapter();
        int bookSetPublicationYearMedian = medianAdapter.publicationYearMedian(books);
        //Then
        assertEquals(2007, bookSetPublicationYearMedian);
    }
}
