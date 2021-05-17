package com.example.clinic;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public abstract class AbsUnitTest {
    @BeforeEach
    protected void setup(){
        MockitoAnnotations.openMocks(this);
    }
}
