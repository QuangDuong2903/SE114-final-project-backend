package com.quangduong.SE114backend;

import com.quangduong.SE114backend.repository.sql.*;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DatabaseTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private DateAttributeRepository dateAttributeRepository;

    @Autowired
    private LabelAttributeRepository labelAttributeRepository;

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NumberAttributeRepository numberAttributeRepository;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TextAttributeRepository textAttributeRepository;

    @Test
    public void allComponentAreNotNull() {
        Assertions.assertThat(dataSource).isNotNull();
        Assertions.assertThat(jdbcTemplate).isNotNull();
        Assertions.assertThat(entityManager).isNotNull();
        Assertions.assertThat(userRepository).isNotNull();
        Assertions.assertThat(boardRepository).isNotNull();
        Assertions.assertThat(dateAttributeRepository).isNotNull();
        Assertions.assertThat(labelAttributeRepository).isNotNull();
        Assertions.assertThat(labelRepository).isNotNull();
        Assertions.assertThat(notificationRepository).isNotNull();
        Assertions.assertThat(numberAttributeRepository).isNotNull();
        Assertions.assertThat(tableRepository).isNotNull();
        Assertions.assertThat(taskRepository).isNotNull();
        Assertions.assertThat(textAttributeRepository).isNotNull();
    }

}
