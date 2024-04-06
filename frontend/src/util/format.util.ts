export default class FormatUtil {
  public static formatDate(dateInput: string) {
    const formattedDate = new Date(dateInput)
    return formattedDate.toLocaleDateString()
  }
}
