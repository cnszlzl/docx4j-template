/*
 * Copyright (c) 2010-2020, vindell (https://github.com/vindell).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.docx4j.template.jsp.engine.runtime.writer;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * 
 * @className	： JspOutputStreamPrinter
 * @description	： TODO(描述这个类的作用)
 * @author 		： <a href="https://github.com/vindell">vindell</a>
 * @date		： 2017年5月24日 下午10:24:37
 * @version 	V1.0
 */
public class JspOutputStreamPrinter extends JspWriter {
    private final OutputStream os;
    private final Charset charset;
    private boolean error;

    public JspOutputStreamPrinter(OutputStream os, Charset charset) {
        this.os = os;
        this.charset = charset;
        this.error = false;
    }

    @Override
    public Object getOriginStream() {
        return os;
    }

    @Override
    public boolean isStreaming() {
        return true;
    }

    @Override
    public Charset getCharset() {
        return charset;
    }

    @Override
    public boolean isSkipErrors() {
        return true;
    }

    @Override
    public void print(int x) {
        if (error) {
            return;
        }
        try {
            os.write(x);
        } catch (IOException e) {
            error = true;
        }
    }

    @Override
    public void print(byte x[]) {
        if (x != null) {
            if (error) {
                return;
            }
            try {
                os.write(x);
            } catch (IOException e) {
                error = true;
            }
        }
    }

    @Override
    public void print(byte x[], int offset, int length) {
        if (x != null) {
            if (error) {
                return;
            }
            try {
                os.write(x, offset, length);
            } catch (IOException e) {
                error = true;
            }
        }
    }

    @Override
    public void print(char x[]) {
        if (x != null) {
            if (error) {
                return;
            }
            try {
                os.write(new String(x).getBytes(charset));
            } catch (IOException e) {
                error = true;
            }
        }
    }

    @Override
    public void print(char x[], int offset, int length) {
        if (x != null) {
            if (error) {
                return;
            }
            try {
                os.write(new String(x, offset, length).getBytes(charset));
            } catch (IOException e) {
                error = true;
            }
        }
    }

    @Override
    public void print(String x) {
        if (x != null) {
            if (error) {
                return;
            }
            try {
                os.write(x.getBytes(charset));
            } catch (IOException e) {
                error = true;
            }
        }
    }

    @Override
    public void flush() {
        if (error) {
            return;
        }
        try {
            os.flush();
        } catch (IOException e) {
            error = true;
        }
    }

    @Override
    public void close() {
        if (error) {
            return;
        }
        try {
            os.close();
        } catch (IOException e) {
            error = true;
        }
    }
}
