# STG file generated by Workcraft 3 (Return of the Hazard), version 3.2.0
.model Untitled
.inputs aA aB aX rO
.outputs aO rA rB rY
.graph
aA+ rY+
aA- aO-
aB+ rY+
aB- aO-
aO+ rO-
aO- p0
aX+ aO+
aX- aO-
rA+ aA+
rA- aA-
rB+ aB+
rB- aB-
rO+ rA+ rB+
rO- rA- rB- rY-
rY+ aX+
rY- aX-
p0 rO+
.marking {p0}
.end
