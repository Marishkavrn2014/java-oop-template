package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] tmpSchoolBook = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        tmpSchoolBook[tmpSchoolBook.length - 1] = book;
        schoolBooks = tmpSchoolBook;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] tmpSchoolBook = new SchoolBook[count()];
        int count = 0;
        for (SchoolBook sb : schoolBooks) {
            if (sb.getName().equals(name)) {
                tmpSchoolBook[count] = sb;
                count++;

            }
        }
        return Arrays.copyOf(tmpSchoolBook, count);
    }

    @Override
    public boolean removeByName(String name) {
        int count = 0;
        boolean result = false;
        SchoolBook[] tmpSchoolBooks = new SchoolBook[schoolBooks.length];
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getName().equals(name)) {
                count++;
                result = true;

            } else {
                tmpSchoolBooks[i - count] = schoolBooks[i];
            }
        }
        schoolBooks = Arrays.copyOf(tmpSchoolBooks, schoolBooks.length - count);
        return result;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
