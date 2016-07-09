package net.liepcki.budgetsentry.history;

import lombok.extern.slf4j.Slf4j;
import net.liepcki.budgetsentry.payment.Payment;
import net.liepcki.budgetsentry.user.CurrentUserProvider;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author gregorry
 */
@RestController
@Slf4j
public class HistoryController {

    public static final String PENDING_PAYMENTS_URI = "/history/pending";

    private final HistoryService historyService;
    private final CurrentUserProvider currentUserProvider;

    private HistoryController(final HistoryService historyService, final CurrentUserProvider currentUserProvider) {
        this.historyService = historyService;
        this.currentUserProvider = currentUserProvider;
    }

    @RequestMapping(
            value = PENDING_PAYMENTS_URI,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public List<Payment> pendingPayments() {
        log.debug("User request for pending payments");
        return historyService.getPendingPayments(currentUserProvider.getCurrentUser());
    }

}
