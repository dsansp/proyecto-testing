package com.example.proyectotesting.patterns_test.creational.builder;

import com.example.proyectotesting.patterns.creational.builder.User;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean married;

    User usuario;
    @BeforeEach
    void setUp() {
        usuario = new User(1L,"María","López","maria@gmail.es", true);
    }
    @DisplayName("Comprobar los getter y setter la clase User")
    @Nested
    class gtsTest {
        @Test
        void getIdTest() {
            Long result = usuario.getId();
            assertEquals(1L, result);
            assertNotEquals(2L, (long) result);
        }

        @Test
        void getFirstNameTest() {
            String result = usuario.getFirstName();
            assertEquals("María", result);
        }

        @Test
        void getLastNameTest() {
            String result = usuario.getLastName();
            assertEquals("López", result);
        }

        @Test
        void getEmailTest() {
            String result = usuario.getEmail();
            assertEquals("maria@gmail.es", result);
        }

        @Test
        void getMarriedTest() {
            boolean result = usuario.getMarried();
            assertTrue(result);
        }

        @Test
        void setIdTest() {
            usuario.setId(2L);
            assertEquals(2L, usuario.getId());
        }

        @Test
        void setFirstNameTest() {
            usuario.setFirstName("Pepe");
            assertEquals("Pepe", usuario.getFirstName());
        }

        @Test
        void serLastNameTest() {
            usuario.setLastName("Barba");
            assertEquals("Barba", usuario.getLastName());
        }

        @Test
        void setEmailTest() {
            usuario.setEmail("pepe@hotmail.com");
            assertEquals("pepe@hotmail.com", usuario.getEmail());
        }

        @Test
        void setMarriedTest() {
            usuario.setMarried(false);
            assertEquals(false, usuario.getMarried());
        }
    }

    @DisplayName("Comprobar la clase static Builder")
    @Nested
    class bldTest {
        @Disabled
        @Test
        void BuilderTest() {

            assertEquals(3L, usuario.getId());
        }

        @Test
        void BuildersetIdTest() {
            new User.Builder().setId(3L);
            Long builderId = new User.Builder().getId();
            assertEquals(null, builderId);
        }

        @Test
        void BuildersetFirstNameTest() {
            //User.Builder usuario2 = new User.Builder();
            new User.Builder().setFirstName("Alan");
            String firstName = new User.Builder().getFirstName();
                    assertEquals(null, firstName);
        }

        @Test
        void BuildersetLastNameTest() {
            new User.Builder().setLastName("Sánchez");
            String lastName = new User.Builder().getLastName();
            assertEquals(null, lastName);
        }

        @Test
        void BuildersetEmailTest() {
            new User.Builder().setEmail("otro@gmail.com");
            String email = new User.Builder().getEmail();
            assertEquals(null, email);
        }

        @Disabled
        @Test
        void BuildersetMarriedTest() {
            new User.Builder().setMarried(false);
            boolean married = new User.Builder().getMarried();
            assertFalse(married);
        }
    }

}