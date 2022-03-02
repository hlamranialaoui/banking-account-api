import com.banking.account.service.AccountService;
import com.banking.account.model.Amount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountServiceTest {

    private AccountService accountService;
    private Amount amount;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void init() {
        accountService = new AccountService();
        amount = accountService.deposit(new BigDecimal(1000));
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void should_return_the_current_amount_when_deposit_a_positive_amount() {
        amount = accountService.deposit(new BigDecimal(500));
        BigDecimal expectedAmount = new BigDecimal(1500);
        assertEquals(expectedAmount, amount.getCurrentAmount());
        assertEquals("Deposit with success",
                outContent.toString().replaceAll("[\r\n]+", ""));
    }

    @Test
    public void should_return_the_current_amount_when_withdrawal_a_positive_amount() {
        amount = accountService.withdrawal(new BigDecimal(500));
        BigDecimal expectedAmount = new BigDecimal(500);
        assertEquals(expectedAmount, amount.getCurrentAmount());
        assertEquals("withdrawal with success",
                outContent.toString().replaceAll("[\r\n]+", ""));
    }

    @Test
    public void should_return_the_current_amount_when_deposit_a_negative_amount() {
        amount = accountService.deposit(new BigDecimal(-5000));
        BigDecimal expectedAmount = new BigDecimal(1000);
        assertEquals(expectedAmount, amount.getCurrentAmount());
        assertEquals("Your amount is null or negative",
                errContent.toString().replaceAll("[\r\n]+", ""));
    }

    @Test
    public void should_return_the_current_amount_when_withdrawal_a_negative_amount() {
        amount = accountService.withdrawal(new BigDecimal(-5000));
        BigDecimal expectedAmount = new BigDecimal(1000);
        assertEquals(expectedAmount, amount.getCurrentAmount());
        assertEquals("Your withdrawal amount is to big to balance, is null or is negative",
                errContent.toString().replaceAll("[\r\n]+", ""));
    }

    @Test
    public void should_return_the_current_amount_when_deposit_is_null() {
        amount = accountService.deposit(null);
        BigDecimal expectedAmount = new BigDecimal(1000);
        assertEquals(expectedAmount, amount.getCurrentAmount());
        assertEquals("Your amount is null or negative",
                errContent.toString().replaceAll("[\r\n]+", ""));
    }

    @Test
    public void should_return_the_current_amount_when_withdrawal_is_null() {
        amount = accountService.withdrawal(null);
        BigDecimal expectedAmount = new BigDecimal(1000);
        assertEquals(expectedAmount, amount.getCurrentAmount());
        assertEquals("Your withdrawal amount is to big to balance, is null or is negative",
                errContent.toString().replaceAll("[\r\n]+", ""));
    }

    @Test
    public void should_return_the_current_amount_when_withdrawal_amount_is_big_than_the_balance() {
        amount = accountService.withdrawal(new BigDecimal(-2000));
        BigDecimal expectedAmount = new BigDecimal(1000);
        assertEquals(expectedAmount, amount.getCurrentAmount());
        assertEquals("Your withdrawal amount is to big to balance, is null or is negative",
                errContent.toString().replaceAll("[\r\n]+", ""));
    }

    @Test
    public void should_have_minimum_one_statement_after_first_deposit_amount(){
        AccountService expectedAccount = new AccountService();
        expectedAccount.deposit(new BigDecimal(1000));
        assertEquals(expectedAccount.statementsNumber(),accountService.statementsNumber());
    }
}
