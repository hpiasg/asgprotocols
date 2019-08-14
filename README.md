ASGprotocols
-------------

ASGprotocols contains all handshake-protocol-specific properties for the ASGtools:
* Style files for Balsa (this is either the entire implementation of the component or just the data path for Resynthesis variants)
* STG blueprints for the control path (just for Resynthesis variants)
* Delay matching configuration

Included protocols:
 * four_b_rb 
     * Original Balsa implementation of four-phase handshaking with broad push and reduced broad pull interface
     * Includes patches for 
         * CallMux: gate implementation instead of tri-state is default
         * CaseFetch: replace tri-state implementation with gate logic and latch
 * four_b_rb_resyn
     * Resynthesis implementation of four-phase handshaking with broad push and reduced broad pull interface
 * Two legacy protocols four_b_rb_legacyB and four_b_rb_legacyR_resyn (They might be removed in future releases)

### Build instructions ###

To build ASGprotocols, Apache Maven v3 (or later) and the Java Development Kit (JDK) v1.7 (or later) are required.

1. Build [ASGcommon](https://github.com/hpiasg/asgcommon)
4. Execute `mvn clean install -DskipTests`