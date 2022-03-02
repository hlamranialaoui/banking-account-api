import com.banking.account.service.AccountService;
import com.banking.account.model.Amount;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountServiceTest {

    private AccountService account;

    @Test
    public void should_return_the_current_amount_when_deposit_a_amount() {
        account = new AccountService();
        Amount amount = account.deposit(new BigDecimal(1000));
        BigDecimal expectedAmount = new BigDecimal(1000);
        assertEquals(expectedAmount, amount.getCurrentAmount());
    }


}
