package pl.klamborowski.catamor.preferences;

import com.orhanobut.hawk.Hawk;

import pl.klamborowski.catamor.model.Account;

/**
 * Created on 2016-07-19.
 */
public enum AccountManager {
    INSTANCE;

    private static final String ACCOUNT = "LoginManager:ACCOUNT";

    public static AccountManager getInstance() {
        return INSTANCE;
    }

    public void flushAccount() {
        Hawk.delete(ACCOUNT);
    }


    public void saveAccount(Account account) {
        Hawk.put(ACCOUNT, account);
    }

    public Account getAccount() {
        return Hawk.get(ACCOUNT);
    }

    public boolean isLoggedIn() {
        return Hawk.contains(ACCOUNT);
    }
}
