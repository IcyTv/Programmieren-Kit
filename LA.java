/**
 * LA
 */
public class LA {
	public static void main(String[] args) {
		for (Symbols a : Symbols.values()) {
			for (Symbols b : Symbols.values()) {
				for (Symbols c : Symbols.values()) {
					Symbol one = new Symbol(Symbols.DOUNUT).mult(a).add(new Symbol(Symbols.PRETZEL).mult(b))
							.add(new Symbol(Symbols.BIKE).mult(c));
					if (!one.equals(Symbols.PRETZEL)) {
						continue;
					}
					Symbol two = new Symbol(Symbols.PRETZEL).mult(a).add(new Symbol(Symbols.DOUNUT).mult(b))
							.add(new Symbol(Symbols.DOUNUT).mult(c));
					if (!two.equals(Symbols.FOOTBALL)) {
						continue;
					}
					Symbol three = new Symbol(Symbols.FOOTBALL).mult(a).add(new Symbol(Symbols.DOUNUT).mult(b))
							.add(new Symbol(Symbols.PRETZEL).mult(c));
					if (!three.equals(Symbols.BIKE)) {
						continue;
					}
					// Don't need to return here, because if there's multiple, something went wrong
					System.out.println("FOUND!! " + a + " " + b + " " + c);
				}
			}
		}
	}
}

enum Symbols {
	BIKE, FOOTBALL, DOUNUT, PRETZEL
}

class Symbol {
	private Symbols symbol;

	public Symbol(Symbols symbol) {
		this.symbol = symbol;
	}

	public Symbol mult(Symbols s) {
		return mult(new Symbol(s));
	}

	public Symbol add(Symbols s) {
		return add(new Symbol(s));
	}

	public Symbol mult(Symbol other) {
		if (this.symbol == Symbols.BIKE || other.symbol == Symbols.BIKE) {
			return new Symbol(Symbols.BIKE);
		}
		if (this.symbol == Symbols.FOOTBALL) {
			return new Symbol(other.symbol);
		}
		if (this.symbol == Symbols.DOUNUT) {
			switch (other.symbol) {
				case FOOTBALL:
					return new Symbol(Symbols.DOUNUT);
				case DOUNUT:
					return new Symbol(Symbols.PRETZEL);
				case PRETZEL:
					return new Symbol(Symbols.FOOTBALL);
				default:
					return null;
			}
		}
		if (this.symbol == Symbols.PRETZEL) {
			switch (other.symbol) {
				case FOOTBALL:
					return new Symbol(Symbols.PRETZEL);
				case DOUNUT:
					return new Symbol(Symbols.FOOTBALL);
				case PRETZEL:
					return new Symbol(Symbols.DOUNUT);
				default:
					return null;
			}
		}
		return null;
	}

	public Symbol add(Symbol other) {
		if (this.symbol == other.symbol) {
			return new Symbol(Symbols.BIKE);
		}
		if (this.symbol == Symbols.FOOTBALL) {
			switch (other.symbol) {
				case BIKE:
					return new Symbol(Symbols.FOOTBALL);
				case DOUNUT:
					return new Symbol(Symbols.PRETZEL);
				case PRETZEL:
					return new Symbol(Symbols.DOUNUT);
				default:
					return null;
			}
		}
		if (this.symbol == Symbols.BIKE) {
			return new Symbol(other.symbol);
		}
		if (this.symbol == Symbols.DOUNUT) {
			switch (other.symbol) {
				case BIKE:
					return new Symbol(Symbols.DOUNUT);
				case FOOTBALL:
					return new Symbol(Symbols.PRETZEL);
				case PRETZEL:
					return new Symbol(Symbols.FOOTBALL);
				default:
					return null;
			}
		}
		if (this.symbol == Symbols.PRETZEL) {
			switch (other.symbol) {
				case BIKE:
					return new Symbol(Symbols.PRETZEL);
				case FOOTBALL:
					return new Symbol(Symbols.DOUNUT);
				case DOUNUT:
					return new Symbol(Symbols.FOOTBALL);
				default:
					return null;
			}
		}
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() == this.getClass()) {
			return this.symbol == ((Symbol) obj).symbol;
		} else if (obj.getClass() == Symbols.class) {
			return this.symbol == (Symbols) obj;
		}
		return false;
	}

	public Symbols getSymbol() {
		return this.symbol;
	}
}