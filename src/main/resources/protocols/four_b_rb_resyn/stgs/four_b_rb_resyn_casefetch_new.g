# STG file generated by Workcraft 3 (Return of the Hazard), version 3.2.3
.model Untitled
.inputs aF aG aI aL aN rO
.outputs aO rD rE rI rK rN
.internal cN
.graph
aF+ rI+
aF- rI-
aG+ aG- unique5
aG- unique10
aI+ rK+
aI- rK-
aL+ unique5
aL- unique10
aN+ cN+
aN- rE+
aO+ rO-
aO- unique0
cN+ rD-
cN- rE-
rD+ rN+
rD- rN-
rE+ unique1
rE- unique6
rI+ aI+
rI- aI-
rK+ aF- unique4
rK- unique9
rN+ aN+
rN- aN-
rO+ rD+
rO- cN-
unique0 rO+
unique1 aF+ aG+
unique10 aO-
unique4 aL+
unique5 aO+
unique6 aF- aG-
unique9 aL-
.marking {unique0}
.end
