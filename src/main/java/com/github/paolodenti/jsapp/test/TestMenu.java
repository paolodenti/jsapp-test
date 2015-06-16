/*
 * Copyright 2015 Paolo Denti
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.paolodenti.jsapp.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

import com.github.paolodenti.jsapp.core.command.Sapp70Command;
import com.github.paolodenti.jsapp.core.command.Sapp71Command;
import com.github.paolodenti.jsapp.core.command.Sapp72Command;
import com.github.paolodenti.jsapp.core.command.Sapp73Command;
import com.github.paolodenti.jsapp.core.command.Sapp74Command;
import com.github.paolodenti.jsapp.core.command.Sapp75Command;
import com.github.paolodenti.jsapp.core.command.Sapp76Command;
import com.github.paolodenti.jsapp.core.command.Sapp77Command;
import com.github.paolodenti.jsapp.core.command.Sapp78Command;
import com.github.paolodenti.jsapp.core.command.Sapp79Command;
import com.github.paolodenti.jsapp.core.command.Sapp7ACommand;
import com.github.paolodenti.jsapp.core.command.Sapp7BCommand;
import com.github.paolodenti.jsapp.core.command.Sapp7CCommand;
import com.github.paolodenti.jsapp.core.command.Sapp7DCommand;
import com.github.paolodenti.jsapp.core.command.Sapp7ECommand;
import com.github.paolodenti.jsapp.core.command.Sapp7FCommand;
import com.github.paolodenti.jsapp.core.command.Sapp80Command;
import com.github.paolodenti.jsapp.core.command.Sapp81Command;
import com.github.paolodenti.jsapp.core.command.Sapp82Command;
import com.github.paolodenti.jsapp.core.command.Sapp90Command;
import com.github.paolodenti.jsapp.core.command.Sapp91Command;
import com.github.paolodenti.jsapp.core.command.base.SappCommand;
import com.github.paolodenti.jsapp.core.command.base.SappConnection;
import com.github.paolodenti.jsapp.core.command.base.SappException;
import com.github.paolodenti.jsapp.core.util.SappUtils;

/**
 * <p>
 * Program for Sapp commands testing.
 * </p>
 *
 * @author Paolo Denti
 */
public class TestMenu {

	private String hostName = null;
	private int portNumber = 0;
	private Scanner input = null;

	public static void main(String[] args) {
		new TestMenu().run(args);
	}

	public void run(String[] args) {

		input = new Scanner(System.in);
		try {
			presentMenu();
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}

	private void presentMenu() {

		boolean exit = false;
		while (!exit) {
			System.out.println("=======================================================");
			System.out.println("                    JSapp test menu");
			System.out.println("=======================================================");

			System.out.println((hostName != null && portNumber != 0) ? String.format("Device address: %s:%d", hostName, portNumber) : "Device address: not yet defined");
			System.out.println();

			System.out.println(" 1) Change device address & port");
			if (hostName != null && portNumber != 0) {
				System.out.println();
				System.out.println("70) execute 0x70 command (Get User Alarm Status)");
				System.out.println("71) execute 0x71 command (Get Message Status)");
				System.out.println("72) execute 0x72 command (Get User Alarm Status 32)");
				System.out.println("73) execute 0x73 command (Get User Message Status 32)");
				System.out.println("74) execute 0x74 command (Get Input Status WORD)");
				System.out.println("75) execute 0x75 command (Get Output Status WORD)");
				System.out.println("76) execute 0x76 command (Get Input Status 32 WORD)");
				System.out.println("77) execute 0x77 command (Get Output Status 32 WORD)");
				System.out.println("78) execute 0x78 command (Set Input Status WORD)");
				System.out.println("79) execute 0x79 command (Set Output Status WORD)");
				System.out.println("7A) execute 0x7A command (Set Input Status 32 WORD)");
				System.out.println("7B) execute 0x7B command (Set Output Status 32 WORD)");
				System.out.println("7C) execute 0x7C command (Get Virtual Status WORD)");
				System.out.println("7D) execute 0x7D command (Set Virtual Status WORD)");
				System.out.println("7E) execute 0x7E command (Get Virtual Status 32 WORD)");
				System.out.println("7F) execute 0x7F command (Set Virtual Status 32 WORD)");
				System.out.println("80) execute 0x80 command (Get Last Output WORD)");
				System.out.println("81) execute 0x81 command (Get Last Input WORD)");
				System.out.println("82) execute 0x82 command (Get Last Virtual WORD)");
				System.out.println("90) execute 0x90 command (Set Bit in Virtual Status WORD)");
				System.out.println("91) execute 0x91 command (Clear Bit in Virtual Status WORD)");
			}

			System.out.println();
			System.out.println("99) Exit");
			System.out.println("=======================================================");
			System.out.println();
			System.out.print("Your choice: ");

			String choice = input.nextLine();
			try {
				int val = Integer.parseInt(choice, 16);

				switch (val) {
				case 1:
				case 0x70:
				case 0x71:
				case 0x72:
				case 0x73:
				case 0x74:
				case 0x75:
				case 0x76:
				case 0x77:
				case 0x78:
				case 0x79:
				case 0x7A:
				case 0x7B:
				case 0x7C:
				case 0x7D:
				case 0x7E:
				case 0x7F:
				case 0x80:
				case 0x81:
				case 0x82:
				case 0x90:
				case 0x91: {
					try {
						Method m = this.getClass().getDeclaredMethod("execute" + choice.toUpperCase());
						m.setAccessible(true);
						Boolean success = (Boolean) m.invoke(this);

						if (success.booleanValue()) {
							alertUser("request completed with success");
						}
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						alertUser("method invoke failed:" + e.getMessage());
					}
					break;
				}

				case 0x99:
					exit = true;
					break;

				default:
					alertUser("bad choice");
					break;
				}
			} catch (NumberFormatException e) {
				alertUser("bad choice");
			}
		}
	}

	protected Boolean execute1() {

		while (true) {
			while (true) {
				System.out.print("Enter device address: ");
				hostName = input.nextLine();
				if (hostName.length() > 0) {
					break;
				}
			}

			while (true) {
				try {
					System.out.print("Enter device port (1-65535): ");
					portNumber = readInt(1, 65535);
					break;
				} catch (NumberFormatException e) {
					alertUser("bad port");
				}
			}

			try {
				SappConnection sappConnection = new SappConnection(hostName, portNumber);
				sappConnection.openConnection();
				sappConnection.closeConnection();
				break;
			} catch (Exception e) {
				alertUser("Cannot open connection to device");
				hostName = null;
				portNumber = 0;

				return Boolean.FALSE;
			}
		}

		return Boolean.TRUE;
	}

	protected Boolean execute70() {

		byte nalm;
		try {
			System.out.print(String.format("Enter alarm number (%d-%d): ", 1, 200));
			nalm = (byte) readInt(1, 200);
		} catch (NumberFormatException e) {
			alertUser("bad alarm number");
			return Boolean.FALSE;
		}

		SappCommand sappCommand;

		try {
			sappCommand = new Sapp70Command(nalm);
			sappCommand.run(hostName, portNumber);
			System.out.println(sappCommand.isResponseOk() ? "raw response: " + sappCommand.getResponse().toString() + " - result: " + SappUtils.prettyPrint(sappCommand) : "command execution failed");
		} catch (SappException e) {
			System.err.println(String.format("Command execution failed: %s", e.getMessage()));
		}

		return Boolean.TRUE;
	}

	protected Boolean execute71() {

		byte nmsg;
		try {
			System.out.print(String.format("Enter message number (%d-%d): ", 1, 250));
			nmsg = (byte) readInt(1, 250);
		} catch (NumberFormatException e) {
			alertUser("bad message number");
			return Boolean.FALSE;
		}

		SappCommand sappCommand;

		try {
			sappCommand = new Sapp71Command(nmsg);
			sappCommand.run(hostName, portNumber);
			System.out.println(sappCommand.isResponseOk() ? "raw response: " + sappCommand.getResponse().toString() + " - result: " + SappUtils.prettyPrint(sappCommand) : "command execution failed");
		} catch (SappException e) {
			System.err.println(String.format("Command execution failed: %s", e.getMessage()));
		}

		return Boolean.TRUE;
	}

	protected Boolean execute72() {

		byte nmod;
		try {
			System.out.print(String.format("Enter alarm number (%d-%d): ", 1, 200));
			nmod = (byte) readInt(1, 200);
		} catch (NumberFormatException e) {
			alertUser("bad alarm number");
			return Boolean.FALSE;
		}

		byte len;
		try {
			System.out.print(String.format("Enter len (%d-%d): ", 1, 32));
			len = (byte) readInt(1, 32);
		} catch (NumberFormatException e) {
			alertUser("bad value");
			return Boolean.FALSE;
		}

		SappCommand sappCommand;

		try {
			sappCommand = new Sapp72Command(nmod, len);
			sappCommand.run(hostName, portNumber);
			System.out.println(sappCommand.isResponseOk() ? "raw response: " + sappCommand.getResponse().toString() + " - result: " + SappUtils.prettyPrint(sappCommand) : "command execution failed");
		} catch (SappException e) {
			System.err.println(String.format("Command execution failed: %s", e.getMessage()));
		}

		return Boolean.TRUE;
	}

	protected Boolean execute73() {

		byte nmsg;
		try {
			System.out.print(String.format("Enter message number (%d-%d): ", 1, 250));
			nmsg = (byte) readInt(1, 250);
		} catch (NumberFormatException e) {
			alertUser("bad message number");
			return Boolean.FALSE;
		}

		byte len;
		try {
			System.out.print(String.format("Enter len (%d-%d): ", 1, 32));
			len = (byte) readInt(1, 32);
		} catch (NumberFormatException e) {
			alertUser("bad value");
			return Boolean.FALSE;
		}

		SappCommand sappCommand;

		try {
			sappCommand = new Sapp73Command(nmsg, len);
			sappCommand.run(hostName, portNumber);
			System.out.println(sappCommand.isResponseOk() ? "raw response: " + sappCommand.getResponse().toString() + " - result: " + SappUtils.prettyPrint(sappCommand) : "command execution failed");
		} catch (SappException e) {
			System.err.println(String.format("Command execution failed: %s", e.getMessage()));
		}

		return Boolean.TRUE;
	}

	protected Boolean execute74() {

		byte nmod;
		try {
			System.out.print(String.format("Enter module number (%d-%d): ", 1, 255));
			nmod = (byte) readInt(1, 255);
		} catch (NumberFormatException e) {
			alertUser("bad module number");
			return Boolean.FALSE;
		}

		SappCommand sappCommand;

		try {
			sappCommand = new Sapp74Command(nmod);
			sappCommand.run(hostName, portNumber);
			System.out.println(sappCommand.isResponseOk() ? "raw response: " + sappCommand.getResponse().toString() + " - result: " + SappUtils.prettyPrint(sappCommand) : "command execution failed");
		} catch (SappException e) {
			System.err.println(String.format("Command execution failed: %s", e.getMessage()));
		}

		return Boolean.TRUE;
	}

	protected Boolean execute75() {

		byte nmod;
		try {
			System.out.print(String.format("Enter module number (%d-%d): ", 1, 255));
			nmod = (byte) readInt(1, 255);
		} catch (NumberFormatException e) {
			alertUser("bad module number");
			return Boolean.FALSE;
		}

		SappCommand sappCommand;

		try {
			sappCommand = new Sapp75Command(nmod);
			sappCommand.run(hostName, portNumber);
			System.out.println(sappCommand.isResponseOk() ? "raw response: " + sappCommand.getResponse().toString() + " - result: " + SappUtils.prettyPrint(sappCommand) : "command execution failed");
		} catch (SappException e) {
			System.err.println(String.format("Command execution failed: %s", e.getMessage()));
		}

		return Boolean.TRUE;
	}

	protected Boolean execute76() {

		byte nmod;
		try {
			System.out.print(String.format("Enter module number (%d-%d): ", 1, 255));
			nmod = (byte) readInt(1, 255);
		} catch (NumberFormatException e) {
			alertUser("bad module number");
			return Boolean.FALSE;
		}

		byte len;
		try {
			System.out.print(String.format("Enter len (%d-%d): ", 1, 32));
			len = (byte) readInt(1, 32);
		} catch (NumberFormatException e) {
			alertUser("bad value");
			return Boolean.FALSE;
		}

		SappCommand sappCommand;

		try {
			sappCommand = new Sapp76Command(nmod, len);
			sappCommand.run(hostName, portNumber);
			System.out.println(sappCommand.isResponseOk() ? "raw response: " + sappCommand.getResponse().toString() + " - result: " + SappUtils.prettyPrint(sappCommand) : "command execution failed");
		} catch (SappException e) {
			System.err.println(String.format("Command execution failed: %s", e.getMessage()));
		}

		return Boolean.TRUE;
	}

	protected Boolean execute77() {

		byte nmod;
		try {
			System.out.print(String.format("Enter module number (%d-%d): ", 1, 255));
			nmod = (byte) readInt(1, 255);
		} catch (NumberFormatException e) {
			alertUser("bad module number");
			return Boolean.FALSE;
		}

		byte len;
		try {
			System.out.print(String.format("Enter len (%d-%d): ", 1, 32));
			len = (byte) readInt(1, 32);
		} catch (NumberFormatException e) {
			alertUser("bad value");
			return Boolean.FALSE;
		}

		SappCommand sappCommand;

		try {
			sappCommand = new Sapp77Command(nmod, len);
			sappCommand.run(hostName, portNumber);
			System.out.println(sappCommand.isResponseOk() ? "raw response: " + sappCommand.getResponse().toString() + " - result: " + SappUtils.prettyPrint(sappCommand) : "command execution failed");
		} catch (SappException e) {
			System.err.println(String.format("Command execution failed: %s", e.getMessage()));
		}

		return Boolean.TRUE;
	}

	protected Boolean execute78() {

		byte nmod;
		try {
			System.out.print(String.format("Enter module number (%d-%d): ", 1, 255));
			nmod = (byte) readInt(1, 255);
		} catch (NumberFormatException e) {
			alertUser("bad module number");
			return Boolean.FALSE;
		}

		int value;
		try {
			System.out.print(String.format("Enter value (%d-%d): ", 0, 0xFFFF));
			value = readInt(0, 0xFFFF);
		} catch (NumberFormatException e) {
			alertUser("bad value");
			return Boolean.FALSE;
		}

		SappCommand sappCommand;

		try {
			sappCommand = new Sapp78Command(nmod, value);
			sappCommand.run(hostName, portNumber);
			System.out.println(sappCommand.isResponseOk() ? "raw response: " + sappCommand.getResponse().toString() + " - result: " + SappUtils.prettyPrint(sappCommand) : "command execution failed");
		} catch (SappException e) {
			System.err.println(String.format("Command execution failed: %s", e.getMessage()));
		}

		return Boolean.TRUE;
	}

	protected Boolean execute79() {

		byte nmod;
		try {
			System.out.print(String.format("Enter module number (%d-%d): ", 1, 255));
			nmod = (byte) readInt(1, 255);
		} catch (NumberFormatException e) {
			alertUser("bad module number");
			return Boolean.FALSE;
		}

		int value;
		try {
			System.out.print(String.format("Enter value (%d-%d): ", 0, 0xFFFF));
			value = readInt(0, 0xFFFF);
		} catch (NumberFormatException e) {
			alertUser("bad value");
			return Boolean.FALSE;
		}

		SappCommand sappCommand;

		try {
			sappCommand = new Sapp79Command(nmod, value);
			sappCommand.run(hostName, portNumber);
			System.out.println(sappCommand.isResponseOk() ? "raw response: " + sappCommand.getResponse().toString() + " - result: " + SappUtils.prettyPrint(sappCommand) : "command execution failed");
		} catch (SappException e) {
			System.err.println(String.format("Command execution failed: %s", e.getMessage()));
		}

		return Boolean.TRUE;
	}

	protected Boolean execute7A() {

		byte nmod;
		try {
			System.out.print(String.format("Enter module number (%d-%d): ", 1, 255));
			nmod = (byte) readInt(1, 255);
		} catch (NumberFormatException e) {
			alertUser("bad module number");
			return Boolean.FALSE;
		}

		byte len;
		try {
			System.out.print(String.format("Enter len (%d-%d): ", 1, 32));
			len = (byte) readInt(1, 32);
		} catch (NumberFormatException e) {
			alertUser("bad value");
			return Boolean.FALSE;
		}

		int[] values = new int[len];
		for (int i = 0; i < values.length; i++) {
			while (true) {
				try {
					System.out.print(String.format("Enter value #%d (%d-%d): ", i + 1, 0, 0xFFFF));
					values[i] = readInt(0, 0xFFFF);
					break;
				} catch (NumberFormatException e) {
					alertUser("bad value");
				}
			}
		}

		SappCommand sappCommand;

		try {
			sappCommand = new Sapp7ACommand(nmod, len, values);
			sappCommand.run(hostName, portNumber);
			System.out.println(sappCommand.isResponseOk() ? "raw response: " + sappCommand.getResponse().toString() + " - result: " + SappUtils.prettyPrint(sappCommand) : "command execution failed");
		} catch (SappException e) {
			System.err.println(String.format("Command execution failed: %s", e.getMessage()));
		}

		return Boolean.TRUE;
	}

	protected Boolean execute7B() {

		byte nmod;
		try {
			System.out.print(String.format("Enter module number (%d-%d): ", 1, 255));
			nmod = (byte) readInt(1, 255);
		} catch (NumberFormatException e) {
			alertUser("bad module number");
			return Boolean.FALSE;
		}

		byte len;
		try {
			System.out.print(String.format("Enter len (%d-%d): ", 1, 32));
			len = (byte) readInt(1, 32);
		} catch (NumberFormatException e) {
			alertUser("bad value");
			return Boolean.FALSE;
		}

		int[] values = new int[len];
		for (int i = 0; i < values.length; i++) {
			while (true) {
				try {
					System.out.print(String.format("Enter value #%d (%d-%d): ", i + 1, 0, 0xFFFF));
					values[i] = readInt(0, 0xFFFF);
					break;
				} catch (NumberFormatException e) {
					alertUser("bad value");
				}
			}
		}

		SappCommand sappCommand;

		try {
			sappCommand = new Sapp7BCommand(nmod, len, values);
			sappCommand.run(hostName, portNumber);
			System.out.println(sappCommand.isResponseOk() ? "raw response: " + sappCommand.getResponse().toString() + " - result: " + SappUtils.prettyPrint(sappCommand) : "command execution failed");
		} catch (SappException e) {
			System.err.println(String.format("Command execution failed: %s", e.getMessage()));
		}

		return Boolean.TRUE;
	}

	protected Boolean execute7C() {

		int nvvar;
		try {
			System.out.print(String.format("Enter address (%d-%d): ", 1, 2500));
			nvvar = readInt(1, 2500);
		} catch (NumberFormatException e) {
			alertUser("bad address");
			return Boolean.FALSE;
		}

		SappCommand sappCommand;

		try {
			sappCommand = new Sapp7CCommand(nvvar);
			sappCommand.run(hostName, portNumber);
			System.out.println(sappCommand.isResponseOk() ? "raw response: " + sappCommand.getResponse().toString() + " - result: " + SappUtils.prettyPrint(sappCommand) : "command execution failed");
		} catch (SappException e) {
			System.err.println(String.format("Command execution failed: %s", e.getMessage()));
		}

		return Boolean.TRUE;
	}

	protected Boolean execute7D() {

		int nvvar;
		try {
			System.out.print(String.format("Enter address (%d-%d): ", 1, 2500));
			nvvar = readInt(1, 2500);
		} catch (NumberFormatException e) {
			alertUser("bad address");
			return Boolean.FALSE;
		}

		int value;
		try {
			System.out.print(String.format("Enter value (%d-%d): ", 0, 0xFFFF));
			value = readInt(0, 0xFFFF);
		} catch (NumberFormatException e) {
			alertUser("bad value");
			return Boolean.FALSE;
		}

		SappCommand sappCommand;

		try {
			sappCommand = new Sapp7DCommand(nvvar, value);
			sappCommand.run(hostName, portNumber);
			System.out.println(sappCommand.isResponseOk() ? "raw response: " + sappCommand.getResponse().toString() + " - result: " + SappUtils.prettyPrint(sappCommand) : "command execution failed");
		} catch (SappException e) {
			System.err.println(String.format("Command execution failed: %s", e.getMessage()));
		}

		return Boolean.TRUE;
	}

	protected Boolean execute7E() {

		int nvvar;
		try {
			System.out.print(String.format("Enter address (%d-%d): ", 1, 2500));
			nvvar = readInt(1, 2500);
		} catch (NumberFormatException e) {
			alertUser("bad address");
			return Boolean.FALSE;
		}

		byte len;
		try {
			System.out.print(String.format("Enter len (%d-%d): ", 1, 32));
			len = (byte) readInt(1, 32);
		} catch (NumberFormatException e) {
			alertUser("bad value");
			return Boolean.FALSE;
		}

		SappCommand sappCommand;

		try {
			sappCommand = new Sapp7ECommand(nvvar, len);
			sappCommand.run(hostName, portNumber);
			System.out.println(sappCommand.isResponseOk() ? "raw response: " + sappCommand.getResponse().toString() + " - result: " + SappUtils.prettyPrint(sappCommand) : "command execution failed");
		} catch (SappException e) {
			System.err.println(String.format("Command execution failed: %s", e.getMessage()));
		}

		return Boolean.TRUE;
	}

	protected Boolean execute7F() {

		int nvvar;
		try {
			System.out.print(String.format("Enter address (%d-%d): ", 1, 2500));
			nvvar = readInt(1, 2500);
		} catch (NumberFormatException e) {
			alertUser("bad address");
			return Boolean.FALSE;
		}

		byte len;
		try {
			System.out.print(String.format("Enter len (%d-%d): ", 1, 32));
			len = (byte) readInt(1, 32);
		} catch (NumberFormatException e) {
			alertUser("bad value");
			return Boolean.FALSE;
		}

		int[] values = new int[len];
		for (int i = 0; i < values.length; i++) {
			while (true) {
				try {
					System.out.print(String.format("Enter value #%d (%d-%d): ", i + 1, 0, 0xFFFF));
					values[i] = readInt(0, 0xFFFF);
					break;
				} catch (NumberFormatException e) {
					alertUser("bad value");
				}
			}
		}

		SappCommand sappCommand;

		try {
			sappCommand = new Sapp7FCommand(nvvar, len, values);
			sappCommand.run(hostName, portNumber);
			System.out.println(sappCommand.isResponseOk() ? "raw response: " + sappCommand.getResponse().toString() + " - result: " + SappUtils.prettyPrint(sappCommand) : "command execution failed");
		} catch (SappException e) {
			System.err.println(String.format("Command execution failed: %s", e.getMessage()));
		}

		return Boolean.TRUE;
	}

	protected Boolean execute80() {

		SappCommand sappCommand;

		try {
			sappCommand = new Sapp80Command();
			sappCommand.run(hostName, portNumber);
			System.out.println(sappCommand.isResponseOk() ? "raw response: " + sappCommand.getResponse().toString() + " - result: " + SappUtils.prettyPrint(sappCommand) : "command execution failed");
		} catch (SappException e) {
			System.err.println(String.format("Command execution failed: %s", e.getMessage()));
		}

		return Boolean.TRUE;
	}

	protected Boolean execute81() {

		SappCommand sappCommand;

		try {
			sappCommand = new Sapp81Command();
			sappCommand.run(hostName, portNumber);
			System.out.println(sappCommand.isResponseOk() ? "raw response: " + sappCommand.getResponse().toString() + " - result: " + SappUtils.prettyPrint(sappCommand) : "command execution failed");
		} catch (SappException e) {
			System.err.println(String.format("Command execution failed: %s", e.getMessage()));
		}

		return Boolean.TRUE;
	}

	protected Boolean execute82() {

		SappCommand sappCommand;

		try {
			sappCommand = new Sapp82Command();
			sappCommand.run(hostName, portNumber);
			System.out.println(sappCommand.isResponseOk() ? "raw response: " + sappCommand.getResponse().toString() + " - result: " + SappUtils.prettyPrint(sappCommand) : "command execution failed");
		} catch (SappException e) {
			System.err.println(String.format("Command execution failed: %s", e.getMessage()));
		}

		return Boolean.TRUE;
	}

	protected Boolean execute90() {

		int nvvar;
		try {
			System.out.print(String.format("Enter address (%d-%d): ", 1, 2500));
			nvvar = readInt(1, 2500);
		} catch (NumberFormatException e) {
			alertUser("bad address");
			return Boolean.FALSE;
		}

		int value;
		try {
			System.out.print(String.format("Enter value (%d-%d): ", 0, 0xFFFF));
			value = readInt(0, 0xFFFF);
		} catch (NumberFormatException e) {
			alertUser("bad value");
			return Boolean.FALSE;
		}

		SappCommand sappCommand;

		try {
			sappCommand = new Sapp90Command(nvvar, value);
			sappCommand.run(hostName, portNumber);
			System.out.println(sappCommand.isResponseOk() ? "raw response: " + sappCommand.getResponse().toString() + " - result: " + SappUtils.prettyPrint(sappCommand) : "command execution failed");
		} catch (SappException e) {
			System.err.println(String.format("Command execution failed: %s", e.getMessage()));
		}

		return Boolean.TRUE;
	}

	protected Boolean execute91() {

		int nvvar;
		try {
			System.out.print(String.format("Enter address (%d-%d): ", 1, 2500));
			nvvar = readInt(1, 2500);
		} catch (NumberFormatException e) {
			alertUser("bad address");
			return Boolean.FALSE;
		}

		int value;
		try {
			System.out.print(String.format("Enter value (%d-%d): ", 0, 0xFFFF));
			value = readInt(0, 0xFFFF);
		} catch (NumberFormatException e) {
			alertUser("bad value");
			return Boolean.FALSE;
		}

		SappCommand sappCommand;

		try {
			sappCommand = new Sapp91Command(nvvar, value);
			sappCommand.run(hostName, portNumber);
			System.out.println(sappCommand.isResponseOk() ? "raw response: " + sappCommand.getResponse().toString() + " - result: " + SappUtils.prettyPrint(sappCommand) : "command execution failed");
		} catch (SappException e) {
			System.err.println(String.format("Command execution failed: %s", e.getMessage()));
		}

		return Boolean.TRUE;
	}

	private void alertUser(String message) {

		System.out.println(message);
		requireEnter();
	}

	private void requireEnter() {

		System.out.println("Press enter to continue");
		input.nextLine();
	}

	private int readInt(int min, int max) throws NumberFormatException {

		int value = Integer.parseInt(input.nextLine());
		if (value < min || value > max) {
			throw new NumberFormatException();
		}

		return value;
	}
}
