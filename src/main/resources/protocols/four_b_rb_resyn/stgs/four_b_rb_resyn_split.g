# STG file generated by Workcraft 3 (Return of the Hazard), version 3.2.0
.model Untitled
.inputs aL aM rI
.outputs aI rL rM
.graph
aI+ rI-
aI- p0
aL+ aI+
aL- aI-
aM+ aI+
aM- aI-
rI+ rL+ rM+
rI- rL- rM-
rL+ aL+
rL- aL-
rM+ aM+
rM- aM-
p0 rI+
.marking {p0}
.end
