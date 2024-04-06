export default class FormatUtil {
  public static formatDate(dateInput?: string) {
    if (!dateInput) {
      return null
    }
    const formattedDate = new Date(dateInput)
    return formattedDate.toLocaleDateString()
  }
}
