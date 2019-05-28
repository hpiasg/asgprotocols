package de.uni_potsdam.hpi.asg.protocols.four_b_rb_resyn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.uni_potsdam.hpi.asg.breeze2stg.io.components.Breeze2STGComponent;
import de.uni_potsdam.hpi.asg.breeze2stg.stg.STGGenerator;
import de.uni_potsdam.hpi.asg.common.breeze.model.HSComponentType;
import de.uni_potsdam.hpi.asg.common.stg.model.Place;
import de.uni_potsdam.hpi.asg.common.stg.model.STG;
import de.uni_potsdam.hpi.asg.common.stg.model.Signal.SignalType;
import de.uni_potsdam.hpi.asg.common.stg.model.Transition;
import de.uni_potsdam.hpi.asg.common.stg.model.Transition.Edge;

public class Sequence extends STGGenerator {

    public Sequence(Breeze2STGComponent comp) {
        super(comp.getBreezename());
    }

    @Override
    public STG generate(int scale, HSComponentType type) {
        int placeId = 0;

        STG stg = new STG();

        // init place
        Place initPlace = stg.getPlaceOrAdd("p" + (placeId++));
        Set<Place> initMarking = new HashSet<>();
        initMarking.add(initPlace);
        stg.setInitMarking(initMarking);

        // rA+
        stg.addSignal("rA", SignalType.input);
        Transition rAp = stg.getTransitionOrAdd("rA", Edge.rising, 0);
        stg.addConnection(initPlace, rAp);

        // rO aO cO+ 0..n-1
        Map<Integer, Place> groupFirstPlace = new HashMap<>();
        Map<Integer, Transition> groupCTransition = new HashMap<>();
        Map<Integer, Transition> groupLastTransition = new HashMap<>();
        for(int i = 0; i < (scale - 1); i++) {
            Place p1 = stg.getPlaceOrAdd("p" + (placeId++));
            groupFirstPlace.put(i, p1);

            stg.addSignal("rO" + i, SignalType.output);
            Transition t1 = stg.getTransitionOrAdd("rO" + i, Edge.rising, 0);
            stg.addConnection(p1, t1);
            Place p2 = stg.getPlaceOrAdd("p" + (placeId++));
            stg.addConnection(t1, p2);

            stg.addSignal("aO" + i, SignalType.input);
            Transition t2 = stg.getTransitionOrAdd("aO" + i, Edge.rising, 0);
            stg.addConnection(p2, t2);
            Place p3 = stg.getPlaceOrAdd("p" + (placeId++));
            stg.addConnection(t2, p3);

            stg.addSignal("cO" + i, SignalType.internal);
            Transition t3 = stg.getTransitionOrAdd("cO" + i, Edge.rising, 0);
            groupCTransition.put(i, t3);
            stg.addConnection(p3, t3);
            Place p4 = stg.getPlaceOrAdd("p" + (placeId++));
            stg.addConnection(t3, p4);

            Transition t4 = stg.getTransitionOrAdd("rO" + i, Edge.falling, 0);
            stg.addConnection(p4, t4);
            Place p5 = stg.getPlaceOrAdd("p" + (placeId++));
            stg.addConnection(t4, p5);

            Transition t5 = stg.getTransitionOrAdd("aO" + i, Edge.falling, 0);
            groupLastTransition.put(i, t5);
            stg.addConnection(p5, t5);
        }

        // rO aO n 
        // aA+ rA-
        Transition lastGroupTJoin = null;
        Transition lastGroupLastTransition = null;
        {
            int i = scale - 1;

            Place p1 = stg.getPlaceOrAdd("p" + (placeId++));
            groupFirstPlace.put(i, p1);

            stg.addSignal("rO" + i, SignalType.output);
            Transition t1 = stg.getTransitionOrAdd("rO" + i, Edge.rising, 0);
            stg.addConnection(p1, t1);
            Place p2 = stg.getPlaceOrAdd("p" + (placeId++));
            stg.addConnection(t1, p2);

            stg.addSignal("aO" + i, SignalType.input);
            Transition t2 = stg.getTransitionOrAdd("aO" + i, Edge.rising, 0);
            stg.addConnection(p2, t2);
            Place p3 = stg.getPlaceOrAdd("p" + (placeId++));
            stg.addConnection(t2, p3);

            stg.addSignal("aA", SignalType.output);
            Transition t3 = stg.getTransitionOrAdd("aA", Edge.rising, 0);
            stg.addConnection(p3, t3);
            Place p4 = stg.getPlaceOrAdd("p" + (placeId++));
            stg.addConnection(t3, p4);

            Transition t4 = stg.getTransitionOrAdd("rA", Edge.falling, 0);
            stg.addConnection(p4, t4);
            Place p5 = stg.getPlaceOrAdd("p" + (placeId++));
            stg.addConnection(t4, p5);

            Transition t5 = stg.getTransitionOrAdd("rO" + i, Edge.falling, 0);
            lastGroupTJoin = t5;
            stg.addConnection(p5, t5);
            Place p6 = stg.getPlaceOrAdd("p" + (placeId++));
            stg.addConnection(t5, p6);

            Transition t6 = stg.getTransitionOrAdd("aO" + i, Edge.falling, 0);
            lastGroupLastTransition = t6;
            stg.addConnection(p6, t6);
        }

        // cO- 0..n-1
        Transition preTrans = lastGroupLastTransition;
        for(int i = 0; i < (scale - 1); i++) {
            Place p1 = stg.getPlaceOrAdd("p" + (placeId++));
            stg.addConnection(preTrans, p1);

            Transition t1 = stg.getTransitionOrAdd("cO" + i, Edge.falling, 0);
            preTrans = t1;
            stg.addConnection(p1, t1);
        }

        // aA-
        {
            Place p1 = stg.getPlaceOrAdd("p" + (placeId++));
            stg.addConnection(preTrans, p1);

            Transition t1 = stg.getTransitionOrAdd("aA", Edge.falling, 0);
            stg.addConnection(p1, t1);
            stg.addConnection(t1, initPlace);
        }

        // S, T types
        stg.addConnection(rAp, groupFirstPlace.get(0));
        char[] types = type.getBeparams().get(1).toString().replaceAll("\"", "").toCharArray();
        for(int i = 0; i < (scale - 1); i++) {
            switch(types[i]) {
                case 'S':
                    stg.addConnection(groupLastTransition.get(i), groupFirstPlace.get(i + 1));
                    break;
                case 'T':
                    stg.addConnection(groupCTransition.get(i), groupFirstPlace.get(i + 1));
                    Place p1 = stg.getPlaceOrAdd("p" + (placeId++));
                    stg.addConnection(groupLastTransition.get(i), p1);
                    stg.addConnection(p1, lastGroupTJoin);
                    break;
                default:
                    System.out.println("Error: Unknown type");
            }
        }

        return stg;
    }
}
