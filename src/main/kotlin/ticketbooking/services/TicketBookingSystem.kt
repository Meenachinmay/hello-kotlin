package ticketbooking.services

import ticketbooking.models.Ticket
import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicInteger

class TicketBookingSystem(private val totalTickets: Int) {
    private val availableTickets = AtomicInteger(totalTickets)
    private val bookedTickets = AtomicInteger(0)

    suspend fun bookTicket(): Boolean {
        if (availableTickets.get() > 0) {
            val booked = synchronized(this) {
                if (availableTickets.get() > 0) {
                    availableTickets.decrementAndGet()
                    bookedTickets.incrementAndGet()
                    true
                } else {
                    false
                }
            }

            if (booked) {
                delay(10) // Simulate some processing time outside the synchronized block
                return true
            }
        }
        return false
    }

    fun getAvailableTickets() = availableTickets.get()
    fun getBookedTickets() = bookedTickets.get()
}