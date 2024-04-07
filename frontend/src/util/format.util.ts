export default class FormatUtil {
  public static formatDate(dateInput?: string): string {
    if (!dateInput) {
      return null
    }
    const formattedDate = new Date(dateInput)
    return formattedDate.toLocaleDateString()
  }
  public static isFutureTime(dateInput?: string): boolean {
    const selectedDate = new Date(dateInput).getTime()
    const currentDate = new Date().getTime()
    return currentDate < selectedDate
  }
}
