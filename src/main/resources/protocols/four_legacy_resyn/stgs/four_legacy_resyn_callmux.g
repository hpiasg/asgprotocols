# STG file generated by Workcraft 3 (Return of the Hazard), version 3.1.9
.model Untitled
.inputs aO aX rI
.outputs aI rO rY
.graph
aI+ rI-
aI- unique0
aO+ unique2
aO- unique4
aX+ rO+
aX- rO-
rI+ rY+
rI- rY-
rO+ aO+
rO- aO-
rY+ aI+ unique1
rY- aI- unique3
unique0 rI+
unique1 aX+
unique2 aI+
unique3 aX-
unique4 aI-
.marking {unique0}
.end
