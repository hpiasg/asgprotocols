package de.uni_potsdam.hpi.asg.protocols.four_b_rb_resyn;

import java.io.File;

import de.uni_potsdam.hpi.asg.breeze2stg.io.components.Breeze2STGComponent;
import de.uni_potsdam.hpi.asg.breeze2stg.stg.STGGenerator;
import de.uni_potsdam.hpi.asg.breeze2stg.stg.STGGeneratorPar;
import de.uni_potsdam.hpi.asg.breeze2stg.stg.STGParserPar;
import de.uni_potsdam.hpi.asg.common.breeze.model.HSComponentType;
import de.uni_potsdam.hpi.asg.common.misc.CommonConstants;
import de.uni_potsdam.hpi.asg.common.stg.GFile;
import de.uni_potsdam.hpi.asg.common.stg.model.STG;

public class CallMux extends STGGenerator {

    private Breeze2STGComponent comp;

    private STGGeneratorPar     scaleTwoGen;
    private STGGeneratorPar     scaleThreeAndMoreGen;

    public CallMux(Breeze2STGComponent comp) {
        super(comp.getBreezename());
        this.comp = comp;
        init();
    }

    private void init() {
        File protocolDir = new File(CommonConstants.DEF_PROTOCOL_DIR_FILE, "four_b_rb_resyn");

        File twoStgFile = new File(protocolDir, "stgs/four_b_rb_resyn_callmux_two.g");
        STG twoStg = GFile.importFromFile(twoStgFile);
        STGParserPar twoParParser = new STGParserPar(comp, twoStg);
        scaleTwoGen = twoParParser.getGenerator();

        File threeAndMoreStgFile = new File(protocolDir, "stgs/four_b_rb_resyn_callmux_threeandmore.g");
        STG threeAndMoreStg = GFile.importFromFile(threeAndMoreStgFile);
        STGParserPar threeAndMoreParParser = new STGParserPar(comp, threeAndMoreStg);
        scaleThreeAndMoreGen = threeAndMoreParParser.getGenerator();
    }

    @Override
    public STG generate(int scale, HSComponentType type) {
        if(scale == 2) {
            return scaleTwoGen.generate(scale);
        }
        return scaleThreeAndMoreGen.generate(scale);
    }
}
