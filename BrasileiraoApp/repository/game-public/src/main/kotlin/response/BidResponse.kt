package response

data class BidResponse(
    val timeOfGame: Int,
    val descriptionOfBid: String,
    val teamImage: String?
)