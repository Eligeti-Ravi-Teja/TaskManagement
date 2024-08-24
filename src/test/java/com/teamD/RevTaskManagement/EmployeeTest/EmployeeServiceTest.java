package com.teamD.RevTaskManagement.EmployeeTest;

import com.teamD.RevTaskManagement.dao.EmployeeDAO;
import com.teamD.RevTaskManagement.exceptions.InvalidCredentialsException;
import com.teamD.RevTaskManagement.model.Employee;
import com.teamD.RevTaskManagement.service.EmployeeService;
import com.teamD.RevTaskManagement.utilities.EmailService;
import com.teamD.RevTaskManagement.utilities.ModelUpdater;
import com.teamD.RevTaskManagement.utilities.PasswordEncrypter;
import com.teamD.RevTaskManagement.utilities.RandomCredentialsGenerator;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {
    @Mock
    private EmployeeDAO employeeDAO;

    @Mock
    private EmailService emailService;

    @Mock
    private RandomCredentialsGenerator generator;

    @Mock
    private ModelUpdater modelUpdater;

    @Mock
    private PasswordEncrypter passwordEncrypter;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee1;
    private Employee employee2;
    private List<Employee> employeeList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        employee1 = new Employee();
        employee1.setEmail("employee1@example.com");
        employee1.setEmployeeName("Employee One");
        employee1.setPassword("hashedPassword1");

        employee2 = new Employee();
        employee2.setEmail("employee2@example.com");
        employee2.setEmployeeName("Employee Two");
        employee2.setPassword("hashedPassword2");

        employeeList = Arrays.asList(employee1, employee2);
    }



    @Test
    void testFetchAllEmployees() {
        when(employeeDAO.findAll()).thenReturn(employeeList);

        List<Employee> employees = employeeService.fetchAllEmployees();

        assertNotNull(employees);
        assertEquals(2, employees.size());
        assertEquals(employee1.getEmail(), employees.get(0).getEmail());
        assertEquals(employee2.getEmail(), employees.get(1).getEmail());
    }

    


}
