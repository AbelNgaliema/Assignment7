package com.example.abeln.myapplication.TestFactories;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import Domain.Author;
import Factories.AuthorFactory;

/**
 * Created by AbelN on 15/04/2016.
 */
public class TestAuthorFactory extends TestCase {

    @Test
    public void testCreate()
    {
        Map<String,String> values = new HashMap<String,String>();
        values.put("name","Boniface");
        values.put("surname", "Kabaso");
        Author author = AuthorFactory.createAuthor(1, values);

        assertEquals("Boniface", author.getName());
        assertEquals("Kabaso", author.getSurname());
        assertEquals(1, author.getId());


    }
    @Test
    public void testUpdate()
    {
        Map<String,String> values = new HashMap<String,String>();
        values.put("name","Hillary");
        values.put("", "Clinton");
        Author author = AuthorFactory.createAuthor(1, values);

        Author newAuthor = new Author.Builder(author.getId()).copy(author).surname("Golderbasehv").build();

        Assert.assertEquals("Hillary", newAuthor.getName());
        Assert.assertEquals("Golderbasehv", newAuthor.getSurname());
        assertEquals(1, newAuthor.getId());
    }

}

