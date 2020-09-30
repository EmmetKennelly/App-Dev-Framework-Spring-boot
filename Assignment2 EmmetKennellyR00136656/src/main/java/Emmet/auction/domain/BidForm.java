package Emmet.auction.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BidForm {
 double price;

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}
 
 
}
