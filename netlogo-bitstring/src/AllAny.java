import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.Reporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;

/**
 * AllAny.java, 
 *
 * Copyright (C) The James Hutton Institute 2015
 *
 * This file is part of netlogo-bitstring
 *
 * netlogo-bitstring is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * netlogo-bitstring is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * netlogo-bitstring. If not, see <http://www.gnu.org/licenses/>. 
 */

/**
 * <!-- AllAny -->
 *
 * @author Gary Polhill
 */
public class AllAny implements Reporter {
	protected enum Mode { ALL_ONE, ALL_ZERO, ANY_ONE, ANY_ZERO };

	private final Mode mode;

	public AllAny(Mode mode) {
		this.mode = mode;
	}

	@Override
	public Syntax getSyntax() {
		return SyntaxJ.reporterSyntax(new int[] { Syntax.WildcardType() }, Syntax.BooleanType());
	}

	/** 
	 * <!-- report -->
	 *
	 * @see org.nlogo.api.Reporter#report(org.nlogo.api.Argument[], org.nlogo.api.Context)
	 */
	@Override
	public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
		NetLogoBitstring bs[] = BitstringExtension.getNetLogoBitstringArgs(args, 0);

		switch(mode) {
		case ALL_ZERO:
			return new Boolean(bs[0].all0());
		case ALL_ONE:
			return new Boolean(bs[0].all1());
		case ANY_ZERO:
			return new Boolean(!bs[0].all1());
		case ANY_ONE:
			return new Boolean(!bs[0].all0());
		default:
			throw new RuntimeException("PANIC!");
		}

	}

}
