For cancelations, you'll want to add a negative value to the revenue metric
because we provided a refund.

Take a look at how `cancelReservation` is implemented in the `ReservationDao`.
You'll notice `totalCost` is already updated to be a negative number. So, that
means you can log the `totalCost` value as is for the revenue metric, similar 
to how the revenue metric would be logged in `BookReservationActivity`.
