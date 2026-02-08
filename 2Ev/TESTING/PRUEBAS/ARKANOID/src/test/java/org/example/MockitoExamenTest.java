package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.omg.CORBA.TypeCodePackage.BadKind;
import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;
import org.omg.CORBA.portable.InputStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class MockitoExamenTest {
/**
    @Test
    public void testMockitoEj1() throws BadKind {
        AddressHelper addressMock = Mockito.mock(AddressHelper.class,RETURNS_DEEP_STUBS);
        InputStream inputMock = Mockito.mock(InputStream.class);

        // Cuando se entre al if que devuelva 1
        when(addressMock.type().content_type().default_index()).thenReturn(1);

        MockitoExamen mockExamen = new MockitoExamen();

        assertThrows(RuntimeException.class, () -> {
            mockExamen.mockitoEj1(addressMock,inputMock);
        });
    }

    @Test
    public void testMockitoEj1Exception() throws BadKind {
        AddressHelper addressMock = Mockito.mock(AddressHelper.class,RETURNS_DEEP_STUBS);
        InputStream inputMock = Mockito.mock(InputStream.class);

        // Cuando se entre al if que devuelva 1
        when(addressMock.type().content_type().default_index()).thenReturn(0);

        MockitoExamen mockExamen = new MockitoExamen();

        assertDoesNotThrow(() -> mockExamen.mockitoEj1(addressMock,inputMock));
    }
    **/
}
