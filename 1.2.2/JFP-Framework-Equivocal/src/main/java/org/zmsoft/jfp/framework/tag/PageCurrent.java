package org.zmsoft.jfp.framework.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.zmsoft.jfp.framework.constants.IFrameworkConstants;

/**
 * 分页标签菜单栏
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class PageCurrent extends TagSupport implements IFrameworkConstants {
	private static final long serialVersionUID = -1687817864059391002L;

	@Override
    public int doEndTag() throws JspException {
        return Tag.EVAL_PAGE;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = null;
        try {
            out = this.pageContext.getOut();
            StringBuilder sb = new StringBuilder();
            //<input type="hidden" name="pageCurrent" id="pageCurrent" value="1">
            sb.append("<input type=\"hidden\" name=\"pageCurrent\" id=\"pageCurrent\" value=\""+value+"\">");
            
            out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            try {
                out.write("<span>PageCurrent标签渲染出错</span>");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return SKIP_BODY;
        }

        return Tag.EVAL_BODY_INCLUDE;
    }

    /////////////////////////////////////////////////////////////////////////////
    private String value = ONE;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
