package com.altimetrik.workshop.vin.decoder;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public abstract class AbstractTest {
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
}
