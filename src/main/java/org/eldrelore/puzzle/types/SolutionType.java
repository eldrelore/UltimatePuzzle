package org.eldrelore.puzzle.types;

import java.util.HashMap;
import java.util.Map;

/**
 * Known solutions (including duplicates), to make it simpler (and faster) to
 * test filtering algorithm.
 * 
 * This was a difficulty when the algorithm took longer (120+ seconds). Now that
 * it takes ~2 seconds, this is less of a concern.
 * 
 * Still nice to have a list of the creator-approved solutions.
 *
 */
public enum SolutionType {
	A("B2->G2->I1->D1->F3->K3->A0->M0->P3->H3->N0->J0->C3->L3->O0->E0"),

	B("B3->F3->G3->N3->P2->L2->K2->H2->C3->J3->E3->A3->D3->I3->M3->O3"),

	C("B3->F3->G3->N3->P2->L2->K2->H2->E3->A3->C3->J3->O3->M3->D3->I3"),

	D("C0->P0->F0->B3->L0->H0->K0->G3->O1->N1->A1->I2->E1->J1->M1->D2"),

	E("D0->C0->P3->B0->I0->J0->L3->F0->M0->E0->K3->G0->O0->A0->H3->N0"),

	G("D0->M3->J3->E3->I0->A3->N3->O3->G1->K2->H2->L2->B1->F2->P2->C2"),

	H("E1->J1->H1->P1->D1->M1->K1->F1->C1->A1->L1->B1->I0->O0->G0->N0"),

	I("E1->L1->J2->I2->H1->P1->C2->D2->K1->F1->A2->M2->G0->B0->N3->O3"),

	J("E2->O2->L1->C1->J2->N2->H1->P1->M2->A2->K1->F1->D3->I3->G0->B0"),

	K("F1->P1->K2->I2->D0->A0->L3->E3->G0->M0->C3->O3->N0->J0->B3->H3"),

	L("F3->I0->A3->M3->E2->G1->K2->N2->H3->B0->C3->O3->L3->J0->D3->P3"),

	M("G0->D3->L0->P0->C0->K3->A0->B0->F0->H3->N0->O0->I0->E3->J0->M0"),

	N("G1->K2->H2->E2->B1->F2->P2->L2->N0->A3->C3->J3->O0->M3->D3->I3"),

	O("G2->I1->C2->M2->K3->A0->B3->O3->J2->E1->P2->L2->H2->D1->F2->N2"),

	P("H0->J0->D3->I0->M1->O1->F2->E1->C1->A1->N2->P1->L1->B1->G2->K1"),

	Q("H1->B1->F2->N2->O1->C1->M2->G2->E1->L1->A2->D2->I0->K0->P3->J3"),

	R("H1->B1->J2->N2->O1->C1->M2->G2->E1->L1->A2->D2->I0->K0->P3->F3"),

	S("H3->J3->K0->G3->D2->E2->A1->I2->F3->P3->B0->C3->N3->L3->O0->M3"),

	T("I1->C2->D2->E2->O1->A2->M2->J2->G1->L2->K2->H2->N1->B2->F2->P2"),

	U("I1->D1->M1->O1->J1->C1->A1->E1->H0->K0->L0->P0->N1->G1->F1->B1"),

	V("I1->D1->M1->O2->J1->C1->A1->N2->L0->P0->F0->B3->E0->H0->K0->G3"),

	W("I1->E2->O2->H2->K1->L2->C2->B2->P0->A3->M3->F3->J0->D3->G3->N3"),

	X("I1->E2->O2->H2->K1->L2->C2->B2->P0->A3->M3->J3->F0->D3->G3->N3"),

	Y("I1->F1->C1->G1->E0->H0->K0->D0->J1->N1->A1->L1->M1->O1->B1->P1"),

	Z("I3->E0->H0->K0->D2->F1->N1->G1->J3->O0->A0->B0->P3->M0->C0->L0"),

	AA("I3->E0->P0->K0->D2->F1->N1->G1->J3->O0->A0->B0->H3->M0->C0->L0"),

	AB("J1->P1->K2->I2->D0->A0->L3->E3->G0->M0->C3->O3->N0->F0->B3->H3"),

	AC("K3->G0->B3->L3->H3->N0->A3->C3->E3->F0->O3->M3->I2->D1->J2->P2"),

	AD("K3->G0->B3->L3->P3->N0->A3->C3->E3->F0->O3->M3->I2->D1->J2->H2"),

	AE("L0->H0->E3->F0->J1->B1->G2->I1->D0->C0->K3->A0->P0->O0->N3->M0"),

	AF("L2->C2->M2->H1->B2->A2->O2->J1->G3->N3->F3->D0->K2->P2->E2->I1"),

	AG("L2->C2->M2->P1->B2->A2->O2->J1->G3->N3->F3->D0->K2->H2->E2->I1"),

	AH("M1->O2->L1->N1->C1->B2->P1->F1->I0->A3->E0->D0->G1->K2->J1->H1"),

	AI("M2->J2->E1->I2->O2->N2->H1->F2->B2->A2->K1->C2->P2->L2->D1->G2"),

	AJ("M2->N1->O2->P2->A2->K1->C2->D2->I3->G0->B3->J3->F2->E1->H2->L2"),

	AK("N0->F0->D3->H0->L0->P0->E3->J0->O1->B1->A2->K1->M0->C0->I3->G0"),

	AL("N1->G1->D1->F2->J1->M1->A1->P2->B0->C0->L0->K3->H0->O0->E0->I3"),

	AM("N1->G1->D1->J2->F1->M1->A1->P2->B0->C0->L0->K3->H0->O0->E0->I3"),

	AN("N2->G2->O2->I2->B3->L3->A3->C3->F3->K3->M3->D3->P3->H3->J3->E3"),

	AO("N2->H1->A2->O2->G2->K1->E2->M2->F2->L1->J2->I2->B2->P1->C2->D2"),

	AP("N2->H1->J2->I2->G2->K1->C2->D2->F2->L1->A2->M2->B2->P1->E2->O2"),

	AQ("O0->E0->P3->B0->M0->A0->L3->F0->D0->C0->K3->G0->I0->J0->H3->N0"),

	AR("O1->M1->I1->D1->A1->E1->J1->C1->H0->K0->L0->P0->N1->G1->F1->B1"),

	AS("O1->N1->B2->G2->M0->A0->F3->K3->D0->C0->P3->H3->I0->J0->L3->E3"),

	AT("P0->F0->B0->N3->H0->K0->L0->G3->J0->M0->A0->O3->E0->D0->C0->I3"),

	AU("P0->J0->D3->I0->M1->O1->F2->E1->C1->A1->N2->H1->L1->B1->G2->K1"),

	AV("P1->D1->J2->L1->O1->C1->B2->H1->N0->K0->G3->E0->M1->A1->I2->F1"),

	AW("P3->B3->O3->M3->L3->A3->N3->J3->D2->K2->H2->E2->G3->C3->F3->I3"),;
	private String solution;

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	private SolutionType(String solution) {
		this.solution = solution;
	}

	private static Map<String, SolutionType> lookup;

	private static void buildLookup() {
		if (null == lookup) {
			lookup = new HashMap<String, SolutionType>();
		}
		for (SolutionType sol : SolutionType.values()) {
			lookup.put(sol.getSolution(), sol);
		}
	}

	static {
		buildLookup();
	}
}
