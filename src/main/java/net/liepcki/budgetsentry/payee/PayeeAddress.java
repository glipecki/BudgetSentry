package net.liepcki.budgetsentry.payee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gregorry
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayeeAddress {

    private String street;
    private String city;
    private String postalCode;

}
