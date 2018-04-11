package de.uni_potsdam.hpi.asg.protocols.io.stgindex;

/*
 * Copyright (C) 2018 Norman Kluge
 * 
 * This file is part of ASGprotocols.
 * 
 * ASGprotocols is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * ASGprotocols is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with ASGprotocols.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.io.File;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@XmlRootElement(name = "stgindex")
@XmlAccessorType(XmlAccessType.NONE)
public class STGIndex {
    protected static final Logger logger = LogManager.getLogger();

    //@formatter:off
    @XmlElement(name = "component")
    private List<STGComponent> components;
    //@formatter:on

    private File                  protocolDir;

    protected STGIndex() {
    }

    public STGIndex(List<STGComponent> components) {
        this.components = components;
    }

    public List<STGComponent> getComponents() {
        return components;
    }

    public File getSTGFileForComponent(String name) {
        for(STGComponent comp : components) {
            if(comp.getBreezename().equals(name)) {
                return new File(protocolDir, comp.getStgFileName());
            }
        }
        return null;
    }

    protected void setProtocolDir(File protocolDir) {
        this.protocolDir = protocolDir;
    }
}
