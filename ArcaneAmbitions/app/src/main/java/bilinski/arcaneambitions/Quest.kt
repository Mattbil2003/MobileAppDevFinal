package bilinski.arcaneambitions


data class Quest(
    val title: String,
    val description: String,
    val time: String,
    val exp_reward: Int,
    val gold_reward: Int
) {
}
