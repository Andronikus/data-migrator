package pt.andronikus.dao;

import pt.andronikus.entities.BillingAccount;
import pt.andronikus.entities.Customer;

import java.util.List;

public interface BillingAccountDao {
    List<BillingAccount> getBillingAccountToCreate(int nbrRecordsToLoad);
    List<BillingAccount> getBillingAccountToClose(int nbrRecordsToLoad);
    boolean updateBillingAccountMigrationState(BillingAccount billingAccount, String migrationStatus);
}
