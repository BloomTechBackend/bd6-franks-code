For reservations that are modified, you'll want to find out the difference between 
the `totalCost` of the original reservation and the modified reservation.

You can use the `UpdatedReservation` object returned by `ReservationDao`'s 
`modifyReservation` method to get both the original and modified reservation data.

Then, you need to subtract the original reservation's `totalCost` from the modified
reservation's `totalCost`. Since `totalCost` is a `BigDecimal`, you can use `BigDecimal`'s
`subtract` method to calculate the difference. The difference would be what you log
for the revenue metric.
