package com.flightreservation.model;

public record FlightReservationTestData(String firstname,String lastname,
                                        String email,
                                        String password,
                                        String street,
                                        String city,
                                        String zip,
                                        int passengercount,
                                        String expectedprice) {
}
