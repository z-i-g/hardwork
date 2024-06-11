package com.zig.hardwork.designtdd.service;

import com.zig.hardwork.designtdd.exception.ApplicationNotFoundException;
import com.zig.hardwork.designtdd.repository.ApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceTest {
    @InjectMocks
    private ApplicationService applicationService;
    @Mock
    private ApplicationRepository applicationRepository;

    @Test
    public void applicationNotFoundExceptionTest() {
        Mockito.when(applicationRepository.findByAppNo(Mockito.anyString())).thenReturn(Optional.empty());
        Exception exception = assertThrows(ApplicationNotFoundException.class, () -> {
            applicationService.findByAppNo("123");
        });

        assertEquals("Заявка 123 не найдена", exception.getLocalizedMessage());
    }
}