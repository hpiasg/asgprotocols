# STG file generated by Workcraft 3 (Return of the Hazard), version 3.2.0
.model Untitled
.inputs aI rO
.outputs aO rI
.graph
aI+/1 unique2
aI-/1 unique4
aO+ rO-
aO- unique0
rI+ aI+/1
rI-/1 aI-/1
rO+/1 aO+ unique1
rO- aO- unique3
unique0 rO+/1
unique1 rI+
unique2 aO+
unique3 rI-/1
unique4 aO-
.marking {unique0}
.end
