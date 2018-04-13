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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.uni_potsdam.hpi.asg.common.misc.CommonConstants;

public class ReadProtocolHelper {
    private static final Logger logger = LogManager.getLogger();

    public static Protocol readFromName(String name) {
        File protocolDir = new File(CommonConstants.DEF_PROTOCOL_DIR_FILE, name);
        if(!protocolDir.exists() || !protocolDir.isDirectory()) {
            logger.error("Could not find protocol dir '" + protocolDir.getAbsolutePath() + "'");
            return null;
        }
        File protocolFile = new File(protocolDir, name + CommonConstants.PROTOCOL_MAIN_FILE_EXTENSION);
        if(!protocolFile.exists()) {
            logger.error("Could not find protocol file '" + protocolFile.getAbsolutePath() + "'");
            return null;
        }
        Protocol protocol = ProtocolFile.readIn(protocolFile);
        if(protocol == null) {
            logger.error("Could not read protocol file '" + protocolFile.getAbsolutePath() + "'");
            return null;
        }
        return protocol;
    }
}
