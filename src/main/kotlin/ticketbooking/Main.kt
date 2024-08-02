package ticketbooking

import kotlinx.coroutines.*
import ticketbooking.services.TicketBookingSystem

suspend fun main() {
    val ticketSystem = TicketBookingSystem(50000)
    val totalAttempts = 250000

    coroutineScope {
        repeat(totalAttempts) {
            launch {
                ticketSystem.bookTicket()
            }
        }
    }

    println("Available tickets: ${ticketSystem.getAvailableTickets()}")
    println("Booked tickets: ${ticketSystem.getBookedTickets()}")
    println("Failed bookings: ${totalAttempts - ticketSystem.getBookedTickets()}")
}