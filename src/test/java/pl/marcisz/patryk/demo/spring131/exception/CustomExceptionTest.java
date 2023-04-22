package pl.marcisz.patryk.demo.spring131.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomExceptionTest {

    @Test
    void shouldSetProvidedMessageToException(){
        //given - parametry wejsciowe lub poczatkowe zalozenia, w tym przypadku message jako parametr
        String message = "informacja o bledzie";

        //when - kod ktory testujemy, sprawdzamy wyniki lub zachowania
        CustomException exception = new CustomException(message);

        //then - jezeli kod z when wykonal sie prawidlowo, to spodziewamy sie ze...
        assertEquals("informacja o bledzie", exception.getMessage());
    }

}
