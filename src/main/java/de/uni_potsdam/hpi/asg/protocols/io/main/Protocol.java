package de.uni_potsdam.hpi.asg.protocols.io.main;

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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@XmlRootElement(name = "protocol")
@XmlAccessorType(XmlAccessType.NONE)
public class Protocol {
    protected static final Logger logger = LogManager.getLogger();

    //@formatter:off
    @XmlElement(name = "name", required = true)
    private String name;
    @XmlElement(name = "balsastyle", required = true)
    private String balsaStyle;
    @XmlElement(name = "delaymatchfile", required = true)
    private String delaymatchFile;
    @XmlElement(name = "stgindexfile")
    private String stgIndexFile;
    //@formatter:on

    private File                  mainDir;

    protected Protocol() {
    }

    protected void setMainDir(File mainDir) {
        this.mainDir = mainDir;
    }

    public String getName() {
        return name;
    }

    public String getBalsaStyle() {
        return balsaStyle;
    }

    public File getDelaymatchFile() {
        return new File(mainDir, delaymatchFile);
    }

    public File getStgIndexFile() {
        return new File(mainDir, stgIndexFile);
    }
}
