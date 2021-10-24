package cooper.storage;

import cooper.finance.FinanceManager;
import cooper.meetings.MeetingManager;
import cooper.verification.Verifier;

public class StorageManager {

    private static final String BASE_DIRECTORY = System.getProperty("user.dir") + "/tmp";
    private final SignInDetailsStorage signInDetailsStorage;
    private final BalanceSheetStorage balanceSheetStorage;
    private final MeetingsStorage meetingsStorage;

    public StorageManager() {
        this.signInDetailsStorage = new SignInDetailsStorage(BASE_DIRECTORY + "/signInDetails.txt");
        this.balanceSheetStorage = new BalanceSheetStorage(BASE_DIRECTORY + "/balanceSheet.txt");
        this.meetingsStorage = new MeetingsStorage(BASE_DIRECTORY + "/meetings.txt");
    }

    public void loadAllData(Verifier cooperVerifier, FinanceManager cooperFinanceManager,
                            MeetingManager cooperMeetingManager) {
        signInDetailsStorage.loadSignInDetails(cooperVerifier);
        balanceSheetStorage.loadBalanceSheet(cooperFinanceManager);
        meetingsStorage.loadMeetings(cooperMeetingManager);
    }

    public void saveSignInDetails(Verifier cooperVerifier) {
        signInDetailsStorage.saveSignInDetails(cooperVerifier);
    }

    public void saveBalanceSheet(FinanceManager cooperFinanceManager) {
        balanceSheetStorage.saveBalanceSheet(cooperFinanceManager);
    }

    public void saveMeetings(MeetingManager cooperMeetingManager) {
        meetingsStorage.saveMeetings(cooperMeetingManager);
    }
}