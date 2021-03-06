import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ChangeHTML {
    public static void main(String[] args) throws IOException {


        String input = "<div style='font-size:0; line-height:0;'></div>\n" +
                "    <table width='100%' border='0' cellpadding='0' cellspacing='0' align='center'>\n" +
                "      <tbody><tr>\n" +
                "        <td align='center' valign='top'>\n" +
                "          \n" +
                "        </td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "        <td align='center'>\n" +
                "          <table cellspacing='0' cellpadding='0' border='0' width='600' class='container' align='center'>\n" +
                "            <tbody><tr>\n" +
                "              <td>\n" +
                "                <table class='tb_properties border_style' style='background-color:#FFFFFF;' cellspacing='0' cellpadding='0' bgcolor='#ffffff' width='100%'>\n" +
                "                  <tbody><tr>\n" +
                "                    <td align='center' valign='top'>\n" +
                "                      <table align='left' border='0' cellpadding='0' cellspacing='0' width='100%'>\n" +
                "                        <tbody><tr>\n" +
                "                          <!-- added padding here -->\n" +
                "                          <td class='content_padding' style=''>\n" +
                "                            <!-- end of comment -->\n" +
                "                            <table border='0' cellpadding='0' cellspacing='0' width='100%'>\n" +
                "                              <tbody><tr> <!-- top slot -->\n" +
                "                                <td align='center' class='header' valign='top'>\n" +
                "                                  <table align='left' border='0' cellpadding='0' cellspacing='0' width='100%'>\n" +
                "                                    <tbody>\n" +
                "                                        <tr>\n" +
                "                                          <td align='left' valign='top'>\n" +
                "                                            <table cellspacing='0' cellpadding='0' style='width:100%'>\n" +
                "                                              <tbody>\n" +
                "                                              <tr>\n" +
                "                                                <td class='responsive-td' valign='top' style='width: 100%;'>\n" +
                "                                                  <!----><table cellpadding='0' cellspacing='0' width='100%' role='presentation' style='min-width: 100%; ' class='stylingblock-content-wrapper'><tbody><tr><td class='stylingblock-content-wrapper camarker-inner'>\n" +
                "\n" +
                "\n" +
                "\n" +
                " \n" +
                "\n" +
                " <title>Santander</title>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                " <!--WEB VIEW-->\n" +
                " <table width='100%' border='0' align='center' cellpadding='0' cellspacing='0' style='max-width: 800px; min-width: 320px;'>\n" +
                "  <tbody><tr>\n" +
                "   <td align='center' style='color:#666; font-size:10px; font-family:Verdana, Geneva, sans-serif'>\n" +
                "    <center>\n" +
                "     Si no puedes ver este mensaje correctamente haz clic\n" +
                "     <a style='color:#666' href='https://view.envio.santander.com.mx/?qs=032e7b8d63038839e14443df942264dab0cb1f6739a233c31fd65f678f06ba5a3f282cd4bfd928f2cd388e43ad5d80e4caab2edd6bd635fe24fb8cd025f247e4fdbbf3b19dd2c385a686d3dd7346e9ac' target='_blank'>aqu??</a>\n" +
                "    </center>\n" +
                "   </td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "   <td height='15'></td>\n" +
                "  </tr>\n" +
                " </tbody></table>\n" +
                " <!--FIN DE WEB VIEW-->\n" +
                "\n" +
                " <!--TABLA GENERAL-->\n" +
                " <table width='100%' bgcolor='#e6e7e9' border='0' cellpadding='0' cellspacing='0' style='width: 100%;font-family: Arial, Helvetica; min-width: 320px; max-width: 600px; margin: 0 auto;'>\n" +
                "  <tbody><tr>\n" +
                "   <td>\n" +
                "    <!--TABLA INTERIOR-->\n" +
                "    <table class='content' align='center' cellpadding='0' cellspacing='0' border='0' style='background-color: #fff; width: 100%; max-width: 600px; min-width: 320px; color:#fff;'>\n" +
                "     <tbody><tr>\n" +
                "      <td width='100%'>\n" +
                "       <!--MAILING-->\n" +
                "       <table class='content' align='center' cellpadding='0' cellspacing='0' border='0' style='background-color: #fff; width: 100%; max-width: 600px; min-width: 320px; color:#fff;'></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table><!----><!----><table cellpadding='0' cellspacing='0' width='100%' role='presentation' style='min-width: 100%; ' class='stylingblock-content-wrapper'><tbody><tr><td class='stylingblock-content-wrapper camarker-inner'>\n" +
                "\n" +
                "\n" +
                "</td></tr></tbody></table><!----><!----><table cellpadding='0' cellspacing='0' width='100%' role='presentation' style='min-width: 100%; ' class='stylingblock-content-wrapper'><tbody><tr><td class='stylingblock-content-wrapper camarker-inner'>\n" +
                "        <!--PERSONALIZACI??N-->\n" +
                "        \n" +
                "         \n" +
                "          <table width='100%' border='0' cellpadding='0' cellspacing='0'>\n" +
                "           <tbody><tr>\n" +
                "            <td width='70%' align='left' valign='middle' style='padding: 0;'>\n" +
                "             <p style='font-size: 10px; font-family: Verdana, Geneva, sans-serif; color: #666;margin: 0;'>Hola, Adriana Amelia Troncoso.</p>\n" +
                "            </td>\n" +
                "            <td width='30%' align='right' valign='middle' style='padding: 0;'>\n" +
                "             <p style='font-size: 10px; font-family: Verdana, Geneva, sans-serif; color: #666;margin: 0;'>Diciembre 2020.</p>\n" +
                "            </td>\n" +
                "           </tr>\n" +
                "          </tbody></table>\n" +
                "         \n" +
                "        \n" +
                "        <!--FIN DE PERSONALIZACI??N-->\n" +
                "        \n" +
                "        \n" +
                "        \n" +
                "         \n" +
                "          \n" +
                "\n" +
                "         \n" +
                "        \n" +
                "        \n" +
                "         \n" +
                "         \n" +
                "          <table width='100%' border='0' cellpadding='0' cellspacing='0'>\n" +
                "           <tbody><tr>\n" +
                "            \n" +
                "            \n" +
                "            \n" +
                "            <td width='600px' align='right' valign='middle' style='padding: 0;'>\n" +
                "             <a href='https://click.envio.santander.com.mx/?qs=020dfc2969d5e73f8ebbb5a0e07831fa649f48039d276a794727a2cacba48237d08e1d8f9b33d5c1e9cbb800d10de6df647db0df500a450a' target='_blank'>\n" +
                "             \n" +
                "             </a>\n" +
                "            </td>\n" +
                "            \n" +
                "           \n" +
                "           </tr>\n" +
                "          </tbody></table>\n" +
                "         \n" +
                "        \n" +
                "        \n" +
                "        \n" +
                "         \n" +
                "          \n" +
                "         \n" +
                "        \n" +
                "        \n" +
                "        \n" +
                "        <!--CONTENIDO-->\n" +
                "      \n" +
                "        \n" +
                "     \n" +
                "        \n" +
                "        \n" +
                "        \n" +
                "        \n" +
                "          \n" +
                "         \n" +
                "<p style='margin:0; padding: 20px 2% 5px; font-size:8px; font-family: Verdana, Geneva, sans-serif; color:#000000;'>Vigencia de la promoci??n y para activarla: del 14 al 27 de diciembre de 2020. Para obtener el 20% de bonificaci??n el primer requisito es inscribirse a la campa??a en S??per Wallet. La bonificaci??n aplica con tarjeta de cr??dito MasterCard a un solo pago o meses sin intereses, a partir de $5,000 pesos. La bonificaci??n aplica en cualquier tienda f??sica Samsung Experience Store. S??lo se consideran clientes que est??n al corriente de sus pagos. Sin monto m??ximo de bonificaci??n, ni l??mite de clientes. Aplica una bonificaci??n por cliente. La bonificaci??n se ver?? re???ejada en el estado de cuenta en un periodo m??ximo de 60 d??as al finalizar la campa??a. No aplica en servicio con devoluci??n o\n" +
                " cancelaci??n. Consulta productos participantes, t??rminos y condiciones en tiendas f??sicas Samsung Experience Store. Solo aplica en compras nacionales. Periodo de aclaraciones: 27 de marzo de 2021. Para las promociones de meses sin intereses consulta directamente en Samsung monto m??nimo de compra, plazo y productos participantes. Para la promoci??n de cupones de descuento, consulta directamente en tiendas el monto de descuento y modelos seleccionados: A115, SM-A217, SM-A315, SMS-A515 y SM-N980 al momento de compra. La promoci??n estar?? vigente del 14 al 27 de diciembre 2020 o hasta agotar existencias. El cup??n no es v??lido por cambio en efectivo. Los cupones aplican en cualquier tienda f??sica Samsung Experience Store. Consulta t??rminos y condiciones en las tiendas Samsung Experience Store.\n" +
                " www.recompensas.santander.com.mx Promedio ponderado del total del portafolio de tarjetas de cr??dito. <strong>CAT promedio <span style='font-size: 22px;'>71.8%</span></strong>, tasa de inter??s promedio ponderada por saldo <strong><span style='font-size: 22px;'>55.33%</span>\n" +
                "</strong> anual variable, comisi??n promedio anual $2,934.79 sin IVA. Calculado al 1 de julio de 2020. Las tarjetas de cr??dito son un producto emitido por Santander Consumo, S.A. de C.V., SOFOM, E.R., Grupo Financiero Santander M??xico, que para su constituci??n y operaci??n con tal car??cter no requiere autorizaci??n de la SHCP. Las tarjetas de d??bito son un producto emitido por Banco Santander M??xico, S.A. Tasa de inter??s para Meses sin Intereses 0.0%. Banco Santander M??xico, S.A., Instituci??n de Banca M??ltiple, Grupo Financiero Santander M??xico. Para mayores informes de contrataciones, beneficios y comisiones del producto, consultar www.santander.com.mx</p>\n" +
                "        \n" +
                "        \n" +
                "        \n" +
                "        \n" +
                "        \n" +
                "        <!--IPAB-->\n" +
                "        \n" +
                "        \n" +
                "        \n" +
                "        \n" +
                "        \n" +
                "         \n" +
                "          \n" +
                "          \n" +
                "          \n" +
                "          \n" +
                "          \n" +
                "          \n" +
                "          \n" +
                "         \n" +
                "          \n" +
                "         \n" +
                "        \n" +
                "          \n" +
                "          <table cellpadding='0' cellspacing='0' border='0' width='100%' style='margin:0 auto;padding: 5px 6% 20px;'>\n" +
                "           <tbody><tr>\n" +
                "            <td width='5%' align='left' valign='middle'>\n" +
                "            \n" +
                "            </td>\n" +
                "            <td width='75%' align='justify' style='color:#000000;text-align:justify;'>\n" +
                "             <p style='margin:0; padding:0; font-size:8px; font-family: Verdana, Geneva, sans-serif;'>Recuerda que Santander nunca solicitar?? que proporciones ning??n tipo de informaci??n confidencial mediante un correo electr??nico o mediante una liga que lleve a nuestra p??gina de internet.\n" +
                "             </p>\n" +
                "            </td>\n" +
                "           </tr>\n" +
                "          </tbody></table>\n" +
                "          \n" +
                "         \n" +
                "        \n" +
                "        <!---FIN DE AVISO PREVENTIVO-->\n" +
                "        <!--FIN DE FOOTER-->\n" +
                "        \n" +
                "        \n" +
                "        <!--IPAB-->\n" +
                "        \n" +
                "        \n" +
                "         \n" +
                "          \n" +
                "          \n" +
                "        \n" +
                "          \n" +
                "          \n" +
                "         \n" +
                "        \n" +
                "        <!---FIN DE AVISO PREVENTIVO-->\n" +
                "        <!--FIN DE FOOTER-->\n" +
                "       \n" +
                "       <!--FIN DE MAILING-->\n" +
                "      \n" +
                "     \n" +
                "    \n" +
                "    <!--FIN DE TABLA INTERIOR-->\n" +
                "   \n" +
                "  \n" +
                " \n" +
                " <!---FIN DE TABLA GENERAL-->\n" +
                "\n" +
                " <!--UNSUSCRIBE-->\n" +
                " <table width='100%' border='0' cellspacing='10px' align='center' cellpadding='0'>\n" +
                "  <tbody><tr>\n" +
                "   <td align='center' style='color:#666; font-size:10px; font-family:Verdana, Geneva, sans-serif'>\n" +
                "    <center>\n" +
                "     Este mensaje fue enviado a adrianatroncosog@gmail.com. Si no eres el usuario\n" +
                "     <br> o si deseas ser borrado de nuestro listado de env??os s??lo haz clic\n" +
                "     <a style='color:#666' href='https://click.envio.santander.com.mx/unsub_center.aspx?qs=ab74f3829a56a7842022b74fd0aff8defe668bbab582d36a7e1baf030cbae0bd1da7d5f8273afcf415d87172023749961257111653c0bf68b16154981676e014ecf8dd7a57b663d6' target='_blank'>aqu??</a>.\n" +
                "    </center>\n" +
                "   </td>\n" +
                "  </tr>\n" +
                " </tbody></table>\n" +
                " <!--FIN DE UNSUSCRIBE-->\n" +
                "\n" +
                "\n" +
                "</td></tr></tbody></table><!----><!----><table cellpadding='0' cellspacing='0' width='100%' role='presentation' style='min-width: 100%; ' class='stylingblock-content-wrapper'><tbody><tr><td class='stylingblock-content-wrapper camarker-inner'>\n" +
                "\n" +
                "<img src='./SantanderAdriana_files/collect' style='visibility: hidden;'>\n" +
                "\n" +
                "</td></tr></tbody></table><!---->\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t  \n" +
                "                                                </td>\n" +
                "                                              </tr>\n" +
                "                                              </tbody>\n" +
                "                                            </table>\n" +
                "                                          </td>\n" +
                "                                        </tr>\n" +
                "                                    </tbody>\n" +
                "                                  </table>\n" +
                "                                </td>\n" +
                "                              </tr>\n" +
                "                            </tbody></table>\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                      </tbody></table>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </tbody></table>\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "        <td valign='top'>\n" +
                "          \n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </tbody></table>";

//        String output = "";
//        try {
//
//            output = new String(input.getBytes("UTF-8"), "ISO-8859-1");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        System.out.print("Output2 - utf,iso: "+output);
        String txt = "";
        txt = new String(input.getBytes("UTF-8"), "ISO-8859-1");
        System.out.print("Output2 - utf,iso: "+txt);

//// Example
//        input = "M??sica";
//        output = "M????sica";

//        String s = "test";
//        try {
//            s.getBytes("UTF-8");
//        } catch(UnsupportedEncodingException uee) {
//            uee.printStackTrace();
        }
//    }
}
