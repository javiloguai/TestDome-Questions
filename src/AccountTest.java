import org.junit.Assert;
import org.junit.Test;

public class AccountTest {
	private double epsilon = 1e-6;

	/*Using JUnit 4's Assert class, write tests for the Account class that cover the following cases:

	The deposit and withdraw methods will not accept negative numbers.
	Account cannot overstep its overdraft limit.
	The deposit and withdraw methods will deposit or withdraw the correct amount, respectively.
	The withdraw and deposit methods return the correct results.*/

	@Test
	public void accountCannotHaveNegativeOverdraftLimit() {
		Account account = new Account(-20);

		Assert.assertEquals(0d, account.getOverdraftLimit(), epsilon);
	}

	@Test
	public void depositAndWithdrawCannotAcceptNegativeNumbers() {
		Account account = new Account(-20);

		Assert.assertFalse(account.deposit(-20));
		Assert.assertFalse(account.withdraw(-20));
	}

	@Test
	public void AccountCannotOverstepOverdraftLimit() {
		Account account = new Account(200);
		account.deposit(2000);
		Assert.assertFalse(account.withdraw(2220));
	}

	@Test
	public void depositAndWithdrawCorrectAmount() {
		Account account = new Account(200);
		account.deposit(2000);
		Assert.assertTrue(account.getBalance()==2000);
		account.withdraw(200);
		Assert.assertTrue(account.getBalance()==1800);
	}

	@Test
	public void withdrawCorrectResults() {
		Account account = new Account(100);
		Assert.assertTrue(account.deposit(2000));		;
		Assert.assertFalse(account.deposit(-20));
		Assert.assertTrue(account.withdraw(20));
		Assert.assertFalse(account.withdraw(-20));
		Assert.assertFalse(account.withdraw(2120));

	}
}