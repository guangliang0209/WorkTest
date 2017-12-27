package mail;

public class MailFactory {
	private MailFactory() {

	}

	private static class Inner {
		private final static Mail mail = new Mail();

	}

	public static Mail getMailInstance() {
		return Inner.mail;
	}
}
