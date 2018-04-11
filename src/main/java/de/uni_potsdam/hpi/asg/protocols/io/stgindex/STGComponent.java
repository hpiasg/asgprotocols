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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.NONE)
public class STGComponent {

    //@formatter:off
    @XmlAttribute(name = "breezename", required = true)
    private String breezename;
    @XmlAttribute(name = "stgfile", required = true)
    private String stgFileName;
    //@formatter:on

    protected STGComponent() {
    }

    public STGComponent(String name, String fileName) {
        this.breezename = name;
        this.stgFileName = fileName;
    }

    public String getBreezename() {
        return breezename;
    }

    public String getStgFileName() {
        return stgFileName;
    }
}
