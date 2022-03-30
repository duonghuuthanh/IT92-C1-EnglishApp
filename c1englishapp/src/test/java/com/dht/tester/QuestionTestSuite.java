/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.tester;

import com.dht.pojo.Choice;
import com.dht.pojo.Question;
import com.dht.services.QuestionServices;
import com.dht.utils.JdbcUitls;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Admin
 */
public class QuestionTestSuite {
    private static Connection conn;
    private static QuestionServices s;
    
    @BeforeAll
    public static void beforeAll() {
        s = new QuestionServices();
        try {
            conn = JdbcUitls.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryTestSuite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterAll
    public static void afterAll() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryTestSuite.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @Test
    public void testSearch01() throws SQLException {
        String kw = "you".toLowerCase();
        List<Question> questions = s.getQuestions(kw);
        
        for (Question q: questions)
            Assertions.assertTrue(q.getContent().toLowerCase().contains(kw), "Ket qua tim kiem ko chinh xac voi id = " +q.getId());
    }
    
    @Test
    public void testSearch02() throws SQLException {
        String kw = "youuuuuuuuuuuuuuuuuuuuuuuuu";
        List<Question> questions = s.getQuestions(kw);
        Assertions.assertEquals(questions.size(), 0);
    }
    
    @Test
    public void testAddQuestion() throws SQLException {
        Question q = new Question(UUID.randomUUID().toString(), "A", 1);
        
        List<Choice> choices = new ArrayList<>();
        choices.add(new Choice(UUID.randomUUID().toString(), "1", true, q.getId()));
        choices.add(new Choice(UUID.randomUUID().toString(), "1", false, q.getId()));
        choices.add(new Choice(UUID.randomUUID().toString(), "1", false, q.getId()));
        choices.add(new Choice(UUID.randomUUID().toString(), "1", false, q.getId()));
        
        Assertions.assertTrue(s.addQuestion(q, choices));
        
        Question newQ = s.getQuestionById(q.getId());
        Assertions.assertNotNull(newQ);
        Assertions.assertEquals(q.getContent(), newQ.getContent());
        Assertions.assertEquals(q.getCategoryId(), newQ.getCategoryId());
    }
    
    @Test
    public void testAddQuestionErr() throws SQLException {
        Question q = new Question(UUID.randomUUID().toString(), "A", 1);
        
        List<Choice> choices = new ArrayList<>();
        choices.add(new Choice(UUID.randomUUID().toString(), "1", true, q.getId()));
        choices.add(new Choice(UUID.randomUUID().toString(), "1", false, q.getId()));
        choices.add(new Choice(UUID.randomUUID().toString(), "1", false, q.getId()));
        
        Assertions.assertFalse(s.addQuestion(q, choices));
        
        Question newQ = s.getQuestionById(q.getId());
        Assertions.assertNull(newQ);
    }
}
