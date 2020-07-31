package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        Author[] tmpAuthors = Arrays.copyOf(authors, authors.length + 1);
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            tmpAuthors[authors.length] = author;
            authors = tmpAuthors;
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Author findByFullName(String name, String lastname) {
        int i = 0;
        while (i < authors.length) {
            if (authors[i].getName().equals(name) && authors[i].getLastName().equals(lastname)) {
                return authors[i];
            }
            i++;
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        int i = 0;
        while (i < authors.length && !authors[i].getName().equals(author.getName()) && !authors[i].getLastName().equals(author.getLastName())) {
            i++;
        }
        if (i < authors.length) {
            Author[] tmpAuthors = new Author[authors.length - 1];
            for (int j = 0; j < tmpAuthors.length; j++) {
                if (j < i - 1) {
                    tmpAuthors[j] = authors[j];
                } else {
                    tmpAuthors[j] = authors[j + 1];
                }
            }
            authors = tmpAuthors;
            return true;
        }
        return false;

    }

    @Override
    public int count() {
        return authors.length;
    }
}
